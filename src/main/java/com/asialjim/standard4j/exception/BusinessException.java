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

/**
 * 业务异常 —— 用户可见的业务错误。实现 {@link ResCode}，可由任意 {@link ResCode}（通常是错误码枚举）构建。
 *
 * <p>典型用法：{@code SomeErrCode.NOT_FOUND.thr("订单不存在")} 或
 * {@code throw SomeErrCode.NOT_FOUND.ex()}。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException implements ResCode {

    private final ResCode resCode;

    public BusinessException(ResCode resCode) {
        this(resCode, resCode.msg());
    }

    public BusinessException(ResCode resCode, String message) {
        super(message);
        this.resCode = resCode;
    }

    public BusinessException(ResCode resCode, String message, Throwable cause) {
        super(message, cause);
        this.resCode = resCode;
    }

    /**
     * 异常所携带的原始错误码定义
     *
     * @return {@link ResCode}
     */
    public ResCode resCode() {
        return this.resCode;
    }

    @Override
    public String code() {
        return this.resCode.code();
    }

    @Override
    public String label() {
        return this.resCode.label();
    }

    @Override
    public String msg() {
        return getMessage();
    }

    @Override
    public int status() {
        return this.resCode.status();
    }
}
