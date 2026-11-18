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
 * 性别 —— 依据 <b>GB/T 2261.1《个人基本信息分类与代码 第1部分：人的性别代码》</b>：
 * {@code 0}=未知、{@code 1}=男、{@code 2}=女、{@code 9}=未说明。
 *
 * <p>本枚举是全库字典枚举的<b>模板</b>：必须含 UNKNOWN 项、提供 {@code unknown()} 静态访问器、
 * 查找三口径统一委托 {@link Codes}。后续字典枚举（民族、学历、证件类型等）照此结构编写。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum Gender implements Code {

    /** 未知性别（GB/T 2261.1: 0） */
    UNKNOWN("0", "未知性别"),

    /** 男（GB/T 2261.1: 1） */
    MALE("1", "男"),

    /** 女（GB/T 2261.1: 2） */
    FEMALE("2", "女"),

    /** 未说明（GB/T 2261.1: 9） */
    UNSTATED("9", "未说明"),
    ;

    private final String code;
    private final String label;

    Gender(String code, String label) {
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
    public static Gender unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link IllegalArgumentException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Gender}
     */
    public static Gender of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link Gender}&gt;
     */
    public static Optional<Gender> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Gender}
     */
    public static Gender ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
