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

import java.util.Objects;

/**
 * 平台应用契约 —— 运行在某个平台上的业务应用。
 *
 * <p>{@link #platform()} 明确回答"该应用属于哪个平台"；平台编码可由
 * {@link #platformCode()} 直接取得，便于持久化与报文传输。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface PlatformApplication extends Code {

    /**
     * 所属平台
     *
     * @return 所属平台，不允许为 {@code null}
     */
    Platform platform();

    /**
     * 所属平台编码
     *
     * @return 平台编码
     */
    default String platformCode() {
        return this.platform().code();
    }

    /**
     * 是否属于指定平台
     *
     * @param platform 平台
     * @return 属于返回 {@code true}
     */
    default boolean belongsTo(Platform platform) {
        if (platform == null)
            return false;
        Platform own = this.platform();
        return own != null && Objects.equals(own.code(), platform.code());
    }
}
