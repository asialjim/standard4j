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

import com.asialjim.standard4j.human.IdCardType;

import java.time.LocalDate;

/**
 * 证件字段契约 —— 证件类型绑定 {@link IdCardType}，其余为证件本体信息。
 *
 * <p>本接口描述<b>单本证件</b>，不是自然人基础字段：一个自然人可持有多本证件，
 * 自然人实体应按需组合 {@code Nameable}/{@code Genderable} 等基础接口，证件另以
 * 集合或独立关联实体承载。</p>
 *
 * <p>字段口径：</p>
 * <ul>
 *     <li>{@code certificateType} —— 证件类型（GA/T 2000.156-2016 主码，兼容 JR/T 0160-2018 金融别名）；</li>
 *     <li>{@code certificateNo} —— 证件号码（校验规则见 {@code validate} 包，本接口不校验）；</li>
 *     <li>{@code certificateName} —— 证件名称（如"中华人民共和国居民身份证"，可空，用于证件影像/复印件展示）；</li>
 *     <li>{@code certificateIssuer} —— 发证机关（可空）；</li>
 *     <li>{@code certificateValidFrom/To} —— 有效期起止（长期有效时 {@code validTo} 为 {@code null}）。</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Certifiable {

    /**
     * 证件类型
     *
     * @return {@link IdCardType}
     */
    IdCardType getCertificateType();

    /**
     * 设置证件类型
     *
     * @param certificateType {@link IdCardType}
     */
    void setCertificateType(IdCardType certificateType);

    /**
     * 证件号码
     *
     * @return 证件号码
     */
    String getCertificateNo();

    /**
     * 设置证件号码
     *
     * @param certificateNo 证件号码
     */
    void setCertificateNo(String certificateNo);

    /**
     * 证件名称（可空）
     *
     * @return 证件名称
     */
    String getCertificateName();

    /**
     * 设置证件名称
     *
     * @param certificateName 证件名称
     */
    void setCertificateName(String certificateName);

    /**
     * 发证机关（可空）
     *
     * @return 发证机关
     */
    String getCertificateIssuer();

    /**
     * 设置发证机关
     *
     * @param certificateIssuer 发证机关
     */
    void setCertificateIssuer(String certificateIssuer);

    /**
     * 证件有效期起（可空）
     *
     * @return 生效日期
     */
    LocalDate getCertificateValidFrom();

    /**
     * 设置证件有效期起
     *
     * @param certificateValidFrom 生效日期
     */
    void setCertificateValidFrom(LocalDate certificateValidFrom);

    /**
     * 证件有效期止（长期有效为 {@code null}）
     *
     * @return 失效日期
     */
    LocalDate getCertificateValidTo();

    /**
     * 设置证件有效期止
     *
     * @param certificateValidTo 失效日期
     */
    void setCertificateValidTo(LocalDate certificateValidTo);

    /**
     * 判定指定日期证件是否在有效期内（起止日均视为有效；{@code validTo} 为 {@code null} 视为长期有效）
     *
     * @param on 判定基准日
     * @return 在有效期内返回 {@code true}
     */
    default boolean certificateValidOn(LocalDate on) {
        if (on == null)
            return false;
        LocalDate from = getCertificateValidFrom();
        LocalDate to = getCertificateValidTo();
        return (from == null || !on.isBefore(from)) && (to == null || !on.isAfter(to));
    }
}
