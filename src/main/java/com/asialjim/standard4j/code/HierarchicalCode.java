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

/**
 * 层级字典接口 —— 在 {@link Code} 之上补充"层级 + 父项"契约。
 *
 * <p>适用于行政区划（GB/T 2260 / GB/T 10114）、国民经济行业分类（GB/T 4754）、
 * 职业分类（GB/T 6565）等层级编码标准。此类标准数据量大且逐年更新，本库
 * <b>只定义契约，不内置数据</b>——谁使用谁实现，实现方自行决定封闭枚举或文件驱动。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface HierarchicalCode extends Code {

    /**
     * 层级（从 0 或 1 起计，依实现方标准而定）
     *
     * @return 层级
     */
    int level();

    /**
     * 上级项编码；顶级项返回 {@code null}
     *
     * @return 上级项编码，顶级为 {@code null}
     */
    String parentCode();
}
