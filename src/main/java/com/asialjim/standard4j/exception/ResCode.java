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

import com.asialjim.standard4j.code.Code;

/**
 * 统一响应码/错误码接口：既是响应码标准，也是异常的码值载体。
 *
 * <p>错误码枚举同样实现 {@link Code} 字典接口，与字典体系共用基础设施；
 * 错误码编码规范：字符串码，模块前缀 + 数字（如 {@code "STD-00001"}）。</p>
 *
 * <p>工厂方法：{@link #ex()} / {@link #ex(String)} 构建 {@link BusinessException}；
 * {@link #thr()} / {@link #thr(String)} 直接抛出。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface ResCode extends Code {

    /**
     * HTTP 语义状态码
     *
     * @return 状态码（如 400/401/403/404/500）
     */
    int status();

    /**
     * 默认消息
     *
     * @return 默认返回 {@link #label()}；异常实例会以实际 message 覆盖
     */
    default String msg() {
        return label();
    }

    /**
     * 构建业务异常
     *
     * @return {@link BusinessException}
     */
    default BusinessException ex() {
        return new BusinessException(this);
    }

    /**
     * 构建业务异常（自定义消息）
     *
     * @param message 消息
     * @return {@link BusinessException}
     */
    default BusinessException ex(String message) {
        return new BusinessException(this, message);
    }

    /**
     * 直接抛出业务异常
     */
    default void thr() {
        throw ex();
    }

    /**
     * 直接抛出业务异常（自定义消息）
     *
     * @param message 消息
     */
    default void thr(String message) {
        throw ex(message);
    }
}
