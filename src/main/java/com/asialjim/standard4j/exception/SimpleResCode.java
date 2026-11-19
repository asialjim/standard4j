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

package com.asialjim.standard4j.exception;

import java.util.Objects;

/**
 * 动态响应码/错误码 —— 用于运行时才能确定的码值。
 *
 * <p>典型场景：从结构化消息（如 {@code SYS-ERR:I/O:TIMEOUT|I/O超时}）解析出
 * 错误码与消息后，构造 {@link BusinessException} 或其他统一响应。</p>
 *
 * @param code   业务错误码
 * @param label  展示消息
 * @param status HTTP 语义状态码；小于等于 0 时按 400 处理
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public record SimpleResCode(String code, String label, int status) implements ResCode {

    public SimpleResCode {
        Objects.requireNonNull(code, "code 不能为空");
        if (code.isBlank())
            throw new IllegalArgumentException("code 不能为空白");
        if (label == null)
            label = "";
        if (status <= 0)
            status = 400;
    }

    /**
     * 构造 HTTP 400 语义的动态响应码
     *
     * @param code  业务错误码
     * @param label 展示消息
     * @return {@link SimpleResCode}
     */
    public static SimpleResCode of(String code, String label) {
        return new SimpleResCode(code, label, 400);
    }

    /**
     * 构造指定 HTTP 状态码的动态响应码
     *
     * @param code   业务错误码
     * @param label  展示消息
     * @param status HTTP 语义状态码
     * @return {@link SimpleResCode}
     */
    public static SimpleResCode of(String code, String label, int status) {
        return new SimpleResCode(code, label, status);
    }
}
