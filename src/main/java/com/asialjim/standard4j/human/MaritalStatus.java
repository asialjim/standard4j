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

package com.asialjim.standard4j.human;

import com.asialjim.standard4j.code.Code;
import com.asialjim.standard4j.code.Codes;

import java.util.Optional;

/**
 * 婚姻状况 —— 依据 <b>GB/T 2261.2-2003《个人基本信息分类与代码 第2部分：婚姻状况代码》</b>：
 * 2 位层次码（第 1 位大类、第 2 位小类）：{@code 10}未婚、{@code 20}已婚（21 初婚/22 再婚/23 复婚）、
 * {@code 30}丧偶、{@code 40}离婚、{@code 90}未说明的婚姻状况。
 *
 * <p>标准自带"未说明"码值 {@code 90}，故 {@link #UNKNOWN} 采用该码值（优于约定的 {@code "-"}）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum MaritalStatus implements Code {

    /** 未说明的婚姻状况（GB/T 2261.2: 90）—— 兼任本字典 UNKNOWN */
    UNKNOWN("90", "未说明的婚姻状况"),

    /** 未婚（GB/T 2261.2: 10） */
    UNMARRIED("10", "未婚"),

    /** 已婚（GB/T 2261.2: 20，大类） */
    MARRIED("20", "已婚"),

    /** 初婚（GB/T 2261.2: 21） */
    FIRST_MARRIAGE("21", "初婚"),

    /** 再婚（GB/T 2261.2: 22） */
    REMARRIAGE("22", "再婚"),

    /** 复婚（GB/T 2261.2: 23） */
    RESTORED_MARRIAGE("23", "复婚"),

    /** 丧偶（GB/T 2261.2: 30） */
    WIDOWED("30", "丧偶"),

    /** 离婚（GB/T 2261.2: 40） */
    DIVORCED("40", "离婚"),
    ;

    private final String code;
    private final String label;

    MaritalStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String label() {
        return this.label;
    }

    /**
     * UNKNOWN 项静态访问器
     *
     * @return {@link #UNKNOWN}
     */
    public static MaritalStatus unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link MaritalStatus}
     */
    public static MaritalStatus of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link MaritalStatus}&gt;
     */
    public static Optional<MaritalStatus> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link MaritalStatus}
     */
    public static MaritalStatus ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
