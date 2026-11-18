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
 * 联系方式字段契约：手机号 + 座机 + 电子邮箱。
 *
 * <p>号码一律以字符串存储（不以数值类型承载，避免前导零丢失与精度问题）；
 * 手机号格式校验属 {@code validate} 包职责（ITU-T E.164 / 工信部号段）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Contactable {

    /**
     * 手机号（可空）
     *
     * @return 手机号
     */
    String getMobile();

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    void setMobile(String mobile);

    /**
     * 固定电话（可空，含区号）
     *
     * @return 固定电话
     */
    String getTelephone();

    /**
     * 设置固定电话
     *
     * @param telephone 固定电话
     */
    void setTelephone(String telephone);

    /**
     * 电子邮箱（可空）
     *
     * @return 电子邮箱
     */
    String getEmail();

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    void setEmail(String email);
}
