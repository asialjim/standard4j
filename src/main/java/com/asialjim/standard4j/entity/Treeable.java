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
 * 树形层级字段契约（行政区划树、组织树、菜单树等）。
 *
 * <p>路径统一为<b>祖先码值以 {@code /} 分隔</b>的形式（如 {@code "/440000/440300/440304"}，
 * 含自身），以支持前缀匹配查询；{@code level} 从 {@code 1} 起计（顶级为 1）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Treeable {

    /**
     * 层级路径（含自身的码值，{@code /} 分隔）
     *
     * @return 层级路径
     */
    String getPath();

    /**
     * 设置层级路径
     *
     * @param path 层级路径
     */
    void setPath(String path);

    /**
     * 层级（顶级为 {@code 1}）
     *
     * @return 层级
     */
    Integer getLevel();

    /**
     * 设置层级
     *
     * @param level 层级
     */
    void setLevel(Integer level);

    /**
     * 上级节点标识（顶级为 {@code null}）
     *
     * @return 上级节点标识
     */
    String getParentId();

    /**
     * 设置上级节点标识
     *
     * @param parentId 上级节点标识
     */
    void setParentId(String parentId);
}
