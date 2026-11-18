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

import com.asialjim.standard4j.code.HierarchicalCode;

/**
 * 行政区划 —— 契约接口，依据 <b>GB/T 2260《中华人民共和国行政区划代码》</b>（6 位省/市/县，
 * 数据逐年变化）与 <b>GB/T 10114《县级以下行政区划代码编制规则》</b>（9 位乡镇级）。
 *
 * <p>数据量大且每年更新，本库<b>不内置数据</b>：谁使用谁实现
 * （封闭枚举、文件驱动、数据库字典表皆可），实现方负责与最新版标准对齐。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface AdministrativeDivision extends HierarchicalCode {
}