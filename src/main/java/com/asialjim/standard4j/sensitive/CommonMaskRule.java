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

import com.asialjim.standard4j.code.Codes;

import java.util.Optional;

/**
 * 常用脱敏规则 —— 覆盖身份证、手机号、银行卡、姓名与邮箱等高频场景。
 *
 * <p>邮箱是"部分敏感"的典型：只有 {@code @} 前的本地部分需要脱敏，
 * 域名原样保留。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum CommonMaskRule implements MaskRule {

    /** 未知/未提供（不脱敏） */
    UNKNOWN("-", "未知", 0, 0) {
        @Override
        public SensitiveSegment sensitiveSegment(String rawValue) {
            return new SensitiveSegment(0, 0);
        }
    },

    /** 身份证号：保留前 6 后 4 */
    ID_CARD("ID_CARD", "身份证号", 6, 4),

    /** 手机号：保留前 3 后 4 */
    MOBILE("MOBILE", "手机号", 3, 4),

    /** 银行卡号：保留前 4 后 4 */
    BANK_CARD("BANK_CARD", "银行卡号", 4, 4),

    /** 中文姓名：保留首尾各 1 个字符 */
    NAME("NAME", "姓名", 1, 1),

    /** 邮箱：只脱敏 {@code @} 前的本地部分，保留首位字符 */
    EMAIL("EMAIL", "邮箱地址", 1, 0) {
        @Override
        public SensitiveSegment sensitiveSegment(String rawValue) {
            return SensitiveSegment.beforeLast(rawValue, '@');
        }
    },
    ;

    private final String code;
    private final String label;
    private final int sensitiveLeft;
    private final int sensitiveRight;

    CommonMaskRule(String code, String label, int sensitiveLeft, int sensitiveRight) {
        this.code = code;
        this.label = label;
        this.sensitiveLeft = sensitiveLeft;
        this.sensitiveRight = sensitiveRight;
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
        return this.sensitiveLeft;
    }

    @Override
    public int sensitiveRight() {
        return this.sensitiveRight;
    }

    /**
     * UNKNOWN 项静态访问器
     *
     * @return {@link #UNKNOWN}
     */
    public static CommonMaskRule unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link CommonMaskRule}
     */
    public static CommonMaskRule of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link CommonMaskRule}&gt;
     */
    public static Optional<CommonMaskRule> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link CommonMaskRule}
     */
    public static CommonMaskRule ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
