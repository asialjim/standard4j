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

package com.asialjim.standard4j.region;

import com.asialjim.standard4j.code.HierarchicalCodeRepository;

/**
 * 行政区划仓库 —— 查询契约（数据与实现由使用方提供）。
 *
 * <p>用法示例：</p>
 * <pre>{@code
 * AdministrativeDivisionRepository repo = ...; // 注入或 ServiceLoader 获取实现
 * AdministrativeDivision district = repo.of("440304");          // 严格查找
 * List<AdministrativeDivision> towns = repo.childrenOf("440304"); // 下级
 * }</pre>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface AdministrativeDivisionRepository extends HierarchicalCodeRepository<AdministrativeDivision> {
}
