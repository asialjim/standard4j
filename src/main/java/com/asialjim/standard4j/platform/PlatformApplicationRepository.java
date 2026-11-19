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

import java.util.List;

/**
 * 平台应用仓库契约 —— 按应用编码或所属平台查询应用。
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface PlatformApplicationRepository extends CodeRepository<PlatformApplication> {

    /**
     * 查询指定平台下的全部应用
     *
     * @param platformCode 平台编码
     * @return 应用列表；无匹配返回空列表（不返回 {@code null}）
     */
    List<PlatformApplication> ofPlatform(String platformCode);

    /**
     * 查询指定平台下的全部应用
     *
     * @param platform 平台
     * @return 应用列表；无匹配返回空列表（不返回 {@code null}）
     */
    default List<PlatformApplication> ofPlatform(Platform platform) {
        return platform == null ? List.of() : ofPlatform(platform.code());
    }
}
