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

package com.asialjim.standard4j.platform;

import com.asialjim.standard4j.code.CodeRepository;

/**
 * 平台仓库契约 —— 平台字典的查询入口，数据与实现由使用方提供。
 *
 * <p>查询口径与 {@link CodeRepository} 一致：{@code of} 严格、{@code ofNullable} 宽容、
 * {@code ofOrDefault} 落 {@link #unknown()}。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface PlatformRepository extends CodeRepository<Platform> {
}
