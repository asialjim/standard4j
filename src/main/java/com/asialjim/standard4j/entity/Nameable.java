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

package com.asialjim.standard4j.entity;

/**
 * 姓名字段契约：姓名 + 曾用名。
 *
 * <p>依据《中华人民共和国户口登记条例》与公安部户籍管理的字段口径：</p>
 * <ul>
 *     <li>{@code name} —— 现用姓名；</li>
 *     <li>{@code formerName} —— 曾用名，无则 {@code null}（不落空串）。</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Nameable {

    /**
     * 现用姓名
     *
     * @return 姓名
     */
    String getName();

    /**
     * 设置现用姓名
     *
     * @param name 姓名
     */
    void setName(String name);

    /**
     * 曾用名（无则为 {@code null}）
     *
     * @return 曾用名
     */
    String getFormerName();

    /**
     * 设置曾用名
     *
     * @param formerName 曾用名
     */
    void setFormerName(String formerName);
}
