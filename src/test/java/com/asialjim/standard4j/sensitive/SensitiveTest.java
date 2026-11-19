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

import com.asialjim.standard4j.human.IdCardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 脱敏契约（{@code sensitive}）测试：敏感段定位、段内掩码、常用规则与 no-op 兜底。
 */
class SensitiveTest {

    @Test
    @DisplayName("规则元数据：保留左右位数与默认掩码字符")
    void ruleMetadata() {
        assertAll(
                () -> assertEquals(6, CommonMaskRule.ID_CARD.sensitiveLeft()),
                () -> assertEquals(4, CommonMaskRule.ID_CARD.sensitiveRight()),
                () -> assertEquals('*', CommonMaskRule.ID_CARD.maskChar()),
                () -> assertEquals("身份证号", CommonMaskRule.ID_CARD.label())
        );
    }

    @Test
    @DisplayName("段内掩码：身份证 6/4、手机号 3/4、银行卡 4/4")
    void segmentMasking() {
        assertAll(
                () -> assertEquals("440304********0012", CommonMaskRule.ID_CARD.maskedValue("440304200006150012")),
                () -> assertEquals("138****8000", CommonMaskRule.MOBILE.maskedValue("13800138000")),
                () -> assertEquals("6222***********0128", CommonMaskRule.BANK_CARD.maskedValue("6222021234567890128"))
        );
    }

    @Test
    @DisplayName("敏感段：邮箱只脱敏 @ 前本地部分，域名原样保留")
    void emailSensitiveSegment() {
        assertAll(
                () -> assertEquals("z*******@example.com", CommonMaskRule.EMAIL.maskedValue("zhangsan@example.com")),
                () -> assertEquals("*@example.com", CommonMaskRule.EMAIL.maskedValue("a@example.com")),
                () -> assertEquals(new SensitiveSegment(0, 8), CommonMaskRule.EMAIL.sensitiveSegment("zhangsan@example.com")),
                () -> assertEquals("zhangsan", "zhangsan@example.com".substring(0, 8))
        );
    }

    @Test
    @DisplayName("短值保护：敏感段不足左右保留位数时仍至少掩码 1 个字符")
    void shortValueProtection() {
        assertAll(
                () -> assertEquals("a*", CommonMaskRule.NAME.maskedValue("ab")),
                () -> assertEquals("*", CommonMaskRule.NAME.maskedValue("a")),
                () -> assertEquals("abcd", CommonMaskRule.UNKNOWN.maskedValue("abcd")),
                () -> assertNull(CommonMaskRule.MOBILE.maskedValue(null))
        );
    }

    @Test
    @DisplayName("IdCardType 直接实现 MaskRule：证件类型自带脱敏规则")
    void idCardTypeAsMaskRule() {
        assertAll(
                () -> assertEquals("440304********0012",
                        IdCardType.RESIDENT_ID_CARD.maskedValue("440304200006150012")),
                () -> assertEquals("E12345*78", IdCardType.PASSPORT.maskedValue("E12345678")),
                () -> assertEquals("440304********0012",
                        Masker.STANDARD.mask("440304200006150012", IdCardType.RESIDENT_ID_CARD))
        );
    }

    @Test
    @DisplayName("Maskable.maskedValue：默认使用标准执行器，显式 no-op 原样返回")
    void masking() {
        Maskable idCard = new Maskable() {
            @Override
            public String rawValue() {
                return "440304200006150012";
            }

            @Override
            public MaskRule maskRule() {
                return CommonMaskRule.ID_CARD;
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
                () -> assertEquals("440304********0012", idCard.maskedValue()),
                () -> assertEquals("440304********0012", idCard.maskedValue(Masker.STANDARD)),
                () -> assertEquals("440304200006150012", idCard.maskedValue(Masker.NONE)),
                () -> assertEquals("440304********0012", idCard.maskedValue(null)),
                () -> assertEquals("440304200006150012", noRule.maskedValue(Masker.STANDARD))
        );
    }

    @Test
    @DisplayName("no-op 兜底：Masker.NONE 支持任意规则但原样返回")
    void noopMasker() {
        assertAll(
                () -> assertEquals(true, Masker.NONE.supports(CommonMaskRule.MOBILE)),
                () -> assertEquals("13800138000", Masker.NONE.mask("13800138000", CommonMaskRule.MOBILE)),
                () -> assertNull(Masker.NONE.mask(null, CommonMaskRule.MOBILE))
        );
    }

    @Test
    @DisplayName("规则查找三口径与 UNKNOWN 兜底")
    void ruleLookup() {
        assertAll(
                () -> assertEquals(CommonMaskRule.ID_CARD, CommonMaskRule.of("ID_CARD")),
                () -> assertEquals(CommonMaskRule.MOBILE, CommonMaskRule.of("mobile")),
                () -> assertEquals(CommonMaskRule.UNKNOWN, CommonMaskRule.ofOrDefault("NOPE")),
                () -> assertEquals(CommonMaskRule.UNKNOWN, CommonMaskRule.unknown())
        );
    }
}
