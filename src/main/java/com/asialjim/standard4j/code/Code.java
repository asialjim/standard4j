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
 * 统一字典模型：所有标准代码（字典枚举、错误码等）统一实现本接口。
 *
 * <p>契约：</p>
 * <ul>
 *     <li>每个封闭字典枚举必须包含 {@code UNKNOWN}（未知/未提供）项，并提供 {@code unknown()} 静态访问器；</li>
 *     <li>查找三口径（经 {@link Codes} 统一实现）：{@code of(code)} 严格匹配抛异常；
 *         {@code ofNullable(code)} 返回 Optional；{@code ofOrDefault(code)} 宽容解析，未匹配返回 UNKNOWN；</li>
 *     <li>展示名方法为 {@link #label()} —— 不可用 {@code name()}，该方法与 {@link Enum#name()}（final，返回枚举常量名）冲突；</li>
 *     <li>序列化三层口径：DB 存 {@link #code()}；前端报文输出 {@link #view()} 结构化数据
 *         （展示 {@code label}、回传 {@code code}）；回传兼容字符串与对象双形态；</li>
 *     <li>枚举常量名与 code 匹配均不区分大小写、容忍首尾空白。</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Code {

    /**
     * 标准编码 —— 字典项唯一的对外表示（DB 存储、接口交换均使用此值）
     *
     * @return 标准编码
     */
    String code();

    /**
     * 展示名（阅读友好，仅用于展示，不落存储/报文）。
     *
     * <p>注意：刻意不叫 {@code name()}，避免与 {@link Enum#name()}（final，返回枚举常量名）冲突。</p>
     *
     * @return 展示名
     */
    String label();

    /**
     * 描述说明
     *
     * @return 默认返回 {@link #label()}
     */
    default String description() {
        return label();
    }

    /**
     * 是否启用
     *
     * @return 默认 {@code true}
     */
    default boolean enabled() {
        return true;
    }

    /**
     * 结构化视图 —— 给前端的展示报文使用：展示 {@code label}、回传 {@code code}
     *
     * @return {@link CodeView}
     */
    default CodeView view() {
        return CodeView.of(this);
    }
}
