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

import com.asialjim.standard4j.exception.BusinessException;
import com.asialjim.standard4j.exception.SystemResCode;

import java.util.Objects;
import java.util.Optional;

/**
 * 字典别名表 SPI —— 同一标准在不同行业码值不同时，用别名映射兼容，不另立枚举。
 *
 * <p>本接口只约定"别名 → 字典项"的查询口径；别名数据可以来自 classpath 文件、
 * 数据库、配置中心或纯代码，由实现方决定。枚举内置别名（如 {@code IdCardType}
 * 的 JR/T 金融码）不需要本接口。</p>
 *
 * @param <T> 字典项类型
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface AliasTable<T extends Code> {

    /**
     * 别名命名空间（如 {@code "JR/T-0160"}），用于诊断与多别名表共存
     *
     * @return 命名空间，不允许为 {@code null}
     */
    String namespace();

    /**
     * 宽容查找：别名 → 字典项
     *
     * @param alias 别名
     * @return 命中返回 {@link Optional}；未命中/无效入参返回 {@link Optional#empty()}
     */
    Optional<T> ofNullable(String alias);

    /**
     * 严格查找：未命中抛 {@link BusinessException}
     *
     * @param alias 别名
     * @return 命中的字典项
     */
    default T of(String alias) {
        return this.ofNullable(alias)
                .orElseThrow(() -> new BusinessException(SystemResCode.UNMATCHED_CODE,
                        "未知的字典别名[" + this.namespace() + "]: [" + alias + "]"));
    }

    /**
     * 宽容解析：未命中落指定 UNKNOWN 项
     *
     * @param alias   别名
     * @param unknown UNKNOWN 项
     * @return 命中的字典项，或 {@code unknown}
     */
    default T ofOrDefault(String alias, T unknown) {
        Objects.requireNonNull(unknown, "unknown 不能为空");
        return this.ofNullable(alias).orElse(unknown);
    }
}
