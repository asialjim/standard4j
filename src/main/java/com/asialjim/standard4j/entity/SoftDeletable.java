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

package com.asialjim.standard4j.entity;

/**
 * 逻辑删除字段契约。
 *
 * <p>用 {@link Boolean} 而非 {@code boolean}：既区分"未设置"与 {@code false}，
 * 也避免沿用 {@code isDeleted()} 在部分序列化框架上的 getter 命名歧义。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface SoftDeletable {

    /**
     * 是否已逻辑删除
     *
     * @return {@code true} 已删除；{@code null} 未设置
     */
    Boolean getDeleted();

    /**
     * 设置逻辑删除标记
     *
     * @param deleted 是否已删除
     */
    void setDeleted(Boolean deleted);
}
