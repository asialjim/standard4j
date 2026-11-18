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

package com.asialjim.standard4j.code;

import java.util.Optional;

/**
 * 字典查找的统一实现：三口径查找、大小写不敏感、容忍首尾空白。
 *
 * <p>所有字典枚举的 {@code of / ofNullable / ofOrDefault} 静态方法都应委托本类，
 * 保证全库查找行为一致。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public abstract class Codes {

    private Codes() {
    }

    /**
     * 严格查找：未匹配抛 {@link IllegalArgumentException}。
     *
     * <p>TODO：{@code exception} 包落地后替换为统一的业务异常（{@code BusinessCode} 口径）。</p>
     *
     * @param values 字典枚举全量值
     * @param code   码值或枚举常量名（不区分大小写，容忍首尾空白）
     * @param <T>    字典类型
     * @return 匹配的字典项
     */
    public static <T extends Code> T of(T[] values, String code) {
        return ofNullable(values, code)
                .orElseThrow(() -> new IllegalArgumentException("未知的字典码值: [" + code + "]"));
    }

    /**
     * 宽容查找：返回 {@link Optional}；入参为 {@code null}/空白/未匹配时返回 {@link Optional#empty()}。
     *
     * @param values 字典枚举全量值
     * @param code   码值或枚举常量名（不区分大小写，容忍首尾空白）
     * @param <T>    字典类型
     * @return {@link Optional}
     */
    public static <T extends Code> Optional<T> ofNullable(T[] values, String code) {
        if (values == null || values.length == 0 || code == null)
            return Optional.empty();
        String trimmed = code.trim();
        if (trimmed.isEmpty())
            return Optional.empty();
        for (T value : values) {
            if (value.code().equalsIgnoreCase(trimmed))
                return Optional.of(value);
            if (value instanceof Enum<?> asEnum && asEnum.name().equalsIgnoreCase(trimmed))
                return Optional.of(value);
        }
        return Optional.empty();
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回指定的 UNKNOWN 项。
     *
     * @param values  字典枚举全量值
     * @param code    码值或枚举常量名（不区分大小写，容忍首尾空白）
     * @param unknown 该字典的 UNKNOWN 项
     * @param <T>     字典类型
     * @return 匹配的字典项，或 UNKNOWN
     */
    public static <T extends Code> T ofOrDefault(T[] values, String code, T unknown) {
        return ofNullable(values, code).orElse(unknown);
    }
}
