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

import com.asialjim.standard4j.code.Code;

/**
 * 平台契约 —— 承载业务应用的运行平台（如官网、App、小程序、开放平台等）。
 *
 * <p>平台本身是标准字典项：{@link #code()} 为平台编码，{@link #label()} 为展示名。
 * 平台的具体码值由使用方定义（封闭枚举、数据库字典表均可），本库不内置任何平台数据。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Platform extends Code {
}
