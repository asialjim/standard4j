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
 * 系统级错误码枚举 —— <b>只收录系统自身故障类错误</b>（依赖不可用、系统繁忙、内部错误等）。
 *
 * <p>边界约定：因用户造成的错误（请求非法、未登录、无权访问、资源不存在等
 * HTTP 4xx 语义）不在本枚举收录，由业务/接入层自行定义错误码枚举并实现
 * {@link ResCode}，避免系统故障码与用户错误码混为一谈。</p>
 *
 * <p>编码规范（模块前缀 + 数字）首批示范：</p>
 * <ul>
 *     <li>系统故障沿用旧库惯例（{@code "-2"} 系统繁忙）与 HTTP 500 语义；</li>
 *     <li>本库自有错误码使用 {@code STD-} 前缀（如 {@code "STD-00001"}）。</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum SystemResCode implements ResCode {

    /** 系统繁忙（沿用旧库 SysBusy 码值 "-2"） */
    SYSTEM_BUSY("-2", "系统繁忙", 400),

    /** 系统内部错误（HTTP 500） */
    INTERNAL_ERROR("500", "系统内部错误", 500),

    /**
     * 未知的字典码值（本库首个 STD- 前缀自有错误码，HTTP 400）。
     *
     * <p>归入系统码的理由：宽容解析（{@code ofNullable}/{@code ofOrDefault}）才是面向用户输入的
     * 正确入口；严格查找（{@code of}）失败意味着调用方未先行校验，属系统内部一致性错误。</p>
     */
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
