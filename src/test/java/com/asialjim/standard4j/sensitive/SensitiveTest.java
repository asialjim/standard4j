/*
 * Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asialjim.standard4j.sensitive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 脱敏契约（{@code sensitive}）测试：规则元数据、SPI 执行、no-op 兜底。
 */
class SensitiveTest {

    /** 示例规则：身份证 6/4、手机号 3/4（与 IdCardType 元数据同口径）。 */
    enum SampleRule implements MaskRule {
        ID_CARD("ID_CARD", "身份证号", 6, 4),
        MOBILE("MOBILE", "手机号", 3, 4),
        ;

        private final String code;
        private final String label;
        private final int left;
        private final int right;

        SampleRule(String code, String label, int left, int right) {
            this.code = code;
            this.label = label;
            this.left = left;
            this.right = right;
        }

        @Override
        public String code() {
            return this.code;
        }

        @Override
        public String label() {
            return this.label;
        }

        @Override
        public int sensitiveLeft() {
            return this.left;
        }

        @Override
        public int sensitiveRight() {
            return this.right;
        }
    }

    /** 示例执行器：真实算法属 sensitive4j，此处仅验证 SPI 契约可被实现。 */
    static final Masker STAR_MASKER = new Masker() {
        @Override
        public boolean supports(MaskRule rule) {
            return rule != null;
        }

        @Override
        public String mask(String rawValue, MaskRule rule) {
            if (rawValue == null || rawValue.length() <= rule.sensitiveLeft() + rule.sensitiveRight())
                return rawValue;
            String left = rawValue.substring(0, rule.sensitiveLeft());
            String right = rawValue.substring(rawValue.length() - rule.sensitiveRight());
            return left + String.valueOf(rule.maskChar())
                    .repeat(rawValue.length() - rule.sensitiveLeft() - rule.sensitiveRight()) + right;
        }
    };

    @Test
    @DisplayName("规则元数据：保留左右位数与默认掩码字符")
    void ruleMetadata() {
        assertAll(
                () -> assertEquals(6, SampleRule.ID_CARD.sensitiveLeft()),
                () -> assertEquals(4, SampleRule.ID_CARD.sensitiveRight()),
                () -> assertEquals('*', SampleRule.ID_CARD.maskChar()),
                () -> assertEquals("身份证号", SampleRule.ID_CARD.label())
        );
    }

    @Test
    @DisplayName("Maskable.maskedValue：按规则脱敏，空值/null 规则/no-op 均原样返回")
    void masking() {
        Maskable idCard = new Maskable() {
            @Override
            public String rawValue() {
                return "440304200006150012";
            }

            @Override
            public MaskRule maskRule() {
                return SampleRule.ID_CARD;
            }
        };
        Maskable noRule = new Maskable() {
            @Override
            public String rawValue() {
                return "440304200006150012";
            }

            @Override
            public MaskRule maskRule() {
                return null;
            }
        };
        assertAll(
                () -> assertEquals("440304********0012", idCard.maskedValue(STAR_MASKER)),
                () -> assertEquals("440304200006150012", idCard.maskedValue(Masker.NONE)),
                () -> assertEquals("440304200006150012", idCard.maskedValue(null)),
                () -> assertEquals("440304200006150012", noRule.maskedValue(STAR_MASKER))
        );
    }

    @Test
    @DisplayName("no-op 兜底：Masker.NONE 支持任意规则但原样返回")
    void noopMasker() {
        assertAll(
                () -> assertEquals(true, Masker.NONE.supports(SampleRule.MOBILE)),
                () -> assertEquals("13800138000", Masker.NONE.mask("13800138000", SampleRule.MOBILE)),
                () -> assertEquals(null, Masker.NONE.mask(null, SampleRule.MOBILE))
        );
    }
}
