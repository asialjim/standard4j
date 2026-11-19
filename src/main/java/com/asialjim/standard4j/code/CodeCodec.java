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

import java.util.Optional;

/**
 * 字典编解码 SPI —— 序列化框架适配层只依赖本接口，本库本体保持零框架依赖。
 *
 * <p>SPI（Service Provider Interface）不是"从文件加载数据"的专指：它是<b>由实现方提供、
 * 由调用方按接口使用</b>的扩展点。可以是 classpath 文件、数据库、远程服务，也可以只是
 * 一段纯 Java 代码；关键在"接口在本库、实现可在别处"。{@link CodeCodec} 让 util4j 的
 * Jackson 适配器与 standard4j 的字典模型解耦。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface CodeCodec {

    /**
     * 编码：字典项 → 存储/报文码值
     *
     * @param code 字典项；可为 {@code null}
     * @return 码值；入参为 {@code null} 时返回 {@code null}
     */
    default String encode(Code code) {
        return code == null ? null : code.code();
    }

    /**
     * 解码：存储/报文码值 → 字典项（宽容口径，未命中返回 {@link Optional#empty()}）
     *
     * @param code   码值
     * @param values 字典枚举全量值
     * @param <T>    字典类型
     * @return {@link Optional}
     */
    default <T extends Code> Optional<T> decode(String code, T[] values) {
        return Codes.ofNullable(values, code);
    }
}
