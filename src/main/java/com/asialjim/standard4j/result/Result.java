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

package com.asialjim.standard4j.result;

import com.asialjim.standard4j.exception.ResCode;

import java.util.List;
import java.util.Objects;

/**
 * 统一响应体 —— 成功/失败、错误码、数据与分页元数据。
 *
 * <p>本类型只描述<b>响应</b>结构：分页请求参数（页码、页大小等）属于调用方入参，
 * 不进入标准库；分页响应元数据由 {@link PageMeta} 承载，包含当前页、总页数、
 * 当前页大小与总记录数。</p>
 *
 * <p>成功响应的码值约定为 {@code "0"}；失败响应的码值与状态码由 {@link ResCode} 提供。</p>
 *
 * @param success 是否成功
 * @param status  HTTP 语义状态码
 * @param code    业务响应码
 * @param message 提示消息
 * @param data    业务数据
 * @param page    分页元数据；非分页响应为 {@code null}
 * @param <T>     业务数据类型
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public record Result<T>(boolean success, int status, String code, String message, T data, PageMeta page) {

    /** 成功响应码 */
    public static final String SUCCESS_CODE = "0";

    /** 成功提示 */
    public static final String SUCCESS_MESSAGE = "OK";

    public Result {
        Objects.requireNonNull(code, "code 不能为空");
        if (message == null)
            message = "";
    }

    /**
     * 成功响应（无数据）
     *
     * @param <T> 业务数据类型
     * @return {@link Result}
     */
    public static <T> Result<T> ok() {
        return new Result<>(true, 200, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
    }

    /**
     * 成功响应
     *
     * @param data 业务数据
     * @param <T>  业务数据类型
     * @return {@link Result}
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(true, 200, SUCCESS_CODE, SUCCESS_MESSAGE, data, null);
    }

    /**
     * 成功响应（带分页元数据）
     *
     * @param data 业务数据
     * @param page 分页元数据
     * @param <T>  业务数据类型
     * @return {@link Result}
     */
    public static <T> Result<T> ok(T data, PageMeta page) {
        return new Result<>(true, 200, SUCCESS_CODE, SUCCESS_MESSAGE, data, page);
    }

    /**
     * 分页成功响应：数据固定为记录列表
     *
     * @param records 当前页记录
     * @param page    分页元数据
     * @param <T>     记录类型
     * @return {@link Result}
     */
    public static <T> Result<List<T>> page(List<T> records, PageMeta page) {
        return ok(records, page);
    }

    /**
     * 失败响应
     *
     * @param resCode 响应码
     * @param <T>     业务数据类型
     * @return {@link Result}
     */
    public static <T> Result<T> fail(ResCode resCode) {
        Objects.requireNonNull(resCode, "resCode 不能为空");
        return new Result<>(false, resCode.status(), resCode.code(), resCode.msg(), null, null);
    }

    /**
     * 失败响应（自定义消息）
     *
     * @param resCode 响应码
     * @param message 自定义消息
     * @param <T>     业务数据类型
     * @return {@link Result}
     */
    public static <T> Result<T> fail(ResCode resCode, String message) {
        Objects.requireNonNull(resCode, "resCode 不能为空");
        return new Result<>(false, resCode.status(), resCode.code(),
                message == null ? resCode.msg() : message, null, null);
    }

    /**
     * 失败响应（显式码值与状态码）
     *
     * @param status  HTTP 语义状态码
     * @param code    业务响应码
     * @param message 提示消息
     * @param <T>     业务数据类型
     * @return {@link Result}
     */
    public static <T> Result<T> fail(int status, String code, String message) {
        return new Result<>(false, status, code, message, null, null);
    }

    /**
     * 是否包含分页元数据
     *
     * @return 包含返回 {@code true}
     */
    public boolean paged() {
        return this.page != null;
    }

    /**
     * 当前页（非分页响应返回 0）
     *
     * @return 当前页
     */
    public int currentPage() {
        return this.page == null ? 0 : this.page.currentPage();
    }

    /**
     * 当前页大小（非分页响应返回 0）
     *
     * @return 页大小
     */
    public int pageSize() {
        return this.page == null ? 0 : this.page.pageSize();
    }

    /**
     * 总页数（非分页响应返回 0）
     *
     * @return 总页数
     */
    public int totalPages() {
        return this.page == null ? 0 : this.page.totalPages();
    }

    /**
     * 总记录数（非分页响应返回 0）
     *
     * @return 总记录数
     */
    public long totalRecords() {
        return this.page == null ? 0 : this.page.totalRecords();
    }
}
