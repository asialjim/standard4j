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
 * 别名解析工具：先按主码值查找，再依次尝试别名表。
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class AliasTables {

    private AliasTables() {
    }

    /**
     * 宽容解析：主码值 → 别名表
     *
     * @param values  字典枚举全量值
     * @param input   主码值或别名
     * @param aliases 别名表（按顺序尝试）
     * @param <T>     字典类型
     * @return 命中返回 {@link Optional}；全部未命中返回 {@link Optional#empty()}
     */
    @SafeVarargs
    public static <T extends Code> Optional<T> ofNullable(T[] values, String input, AliasTable<T>... aliases) {
        Optional<T> direct = Codes.ofNullable(values, input);
        if (direct.isPresent())
            return direct;
        if (aliases == null)
            return Optional.empty();
        for (AliasTable<T> table : aliases) {
            if (table == null)
                continue;
            Optional<T> hit = table.ofNullable(input);
            if (hit.isPresent())
                return hit;
        }
        return Optional.empty();
    }
}
