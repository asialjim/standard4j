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
 * 系统级错误码枚举 —— 错误码规范（模块前缀 + 数字）的首批示范：
 * <ul>
 *     <li>通用系统码沿用 HTTP 语义数字（400/401/403/404/500）与旧库惯例（{@code "-2"} 系统繁忙）；</li>
 *     <li>本库自有错误码使用 {@code STD-} 前缀（如 {@code "STD-00001"}）。</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum SystemResCode implements ResCode {

    /** 请求非法（HTTP 400） */
    BAD_REQUEST("400", "请求非法", 400),

    /** 未登录或会话已过期（HTTP 401） */
    UNAUTHORIZED("401", "未登录或会话已过期", 401),

    /** 无权访问（HTTP 403） */
    FORBIDDEN("403", "无权访问", 403),

    /** 资源不存在（HTTP 404） */
    NOT_FOUND("404", "资源不存在", 404),

    /** 系统繁忙（沿用旧库 SysBusy 码值 "-2"） */
    SYSTEM_BUSY("-2", "系统繁忙", 400),

    /** 系统内部错误（HTTP 500） */
    INTERNAL_ERROR("500", "系统内部错误", 500),

    /** 未知的字典码值（本库首个 STD- 前缀自有错误码） */
    UNMATCHED_CODE("STD-00001", "未知的字典码值", 400),
    ;

    private final String code;
    private final String label;
    private final int status;

    SystemResCode(String code, String label, int status) {
        this.code = code;
        this.label = label;
        this.status = status;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String label() {
        return this.label;
    }

    @Override
    public int status() {
        return this.status;
    }
}
