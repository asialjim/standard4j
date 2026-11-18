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

import com.asialjim.standard4j.human.Gender;

/**
 * 性别字段契约 —— 类型绑定 {@link Gender}（GB/T 2261.1），不接受裸字符串。
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Genderable {

    /**
     * 性别
     *
     * @return {@link Gender}
     */
    Gender getGender();

    /**
     * 设置性别
     *
     * @param gender {@link Gender}
     */
    void setGender(Gender gender);
}
