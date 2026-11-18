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

package com.asialjim.standard4j.industry;

import com.asialjim.standard4j.code.HierarchicalCode;

/**
 * 行业 —— 契约接口，依据 <b>GB/T 4754《国民经济行业分类》</b>：
 * 门类（A~T 字母）→ 大类（2 位）→ 中类（4 位）→ 小类（6 位）的层级编码。
 *
 * <p>分类体系庞大且定期修订，本库<b>不内置数据</b>：谁使用谁实现，
 * 实现方按自身业务需要的层级深度对齐现行版标准。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Industry extends HierarchicalCode {
}
