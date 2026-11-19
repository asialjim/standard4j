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
 * 字典编解码器的默认实现入口。
 *
 * <p>默认实现直接委托 {@link Codes}，与枚举静态查找保持同一口径：
 * 不区分大小写、容忍首尾空白、未命中返回空 {@link java.util.Optional}。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class CodeCodecs {

    /**
     * 默认编码器：{@code encode} 取 {@link Code#code()}，{@code decode} 委托 {@link Codes}
     */
    public static final CodeCodec DEFAULT = new CodeCodec() {
    };

    private CodeCodecs() {
    }
}
