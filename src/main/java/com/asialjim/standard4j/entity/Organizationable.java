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
 * 组织字段契约：名称 + 统一社会信用代码 + 法定代表人。
 *
 * <p>统一社会信用代码遵循 <b>GB 32100-2015</b>（18 位），校验规则属 {@code validate} 包职责；
 * 法定代表人姓名字段遵循 {@link Nameable} 的姓名口径。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Organizationable {

    /**
     * 组织名称（工商登记全称）
     *
     * @return 组织名称
     */
    String getOrganizationName();

    /**
     * 设置组织名称
     *
     * @param organizationName 组织名称
     */
    void setOrganizationName(String organizationName);

    /**
     * 统一社会信用代码（GB 32100-2015，18 位）
     *
     * @return 统一社会信用代码
     */
    String getUnifiedSocialCreditCode();

    /**
     * 设置统一社会信用代码
     *
     * @param unifiedSocialCreditCode 统一社会信用代码
     */
    void setUnifiedSocialCreditCode(String unifiedSocialCreditCode);

    /**
     * 法定代表人姓名
     *
     * @return 法定代表人姓名
     */
    String getLegalRepresentative();

    /**
     * 设置法定代表人姓名
     *
     * @param legalRepresentative 法定代表人姓名
     */
    void setLegalRepresentative(String legalRepresentative);
}
