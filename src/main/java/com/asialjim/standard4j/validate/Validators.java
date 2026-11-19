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

package com.asialjim.standard4j.validate;

/**
 * 内置校验器统一入口。
 *
 * <p>所有校验器均实现 {@link Validator}，可单独实例化，也可直接使用本类常量。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class Validators {

    /** 公民身份号码（GB 11643-1999） */
    public static final Validator<String> ID_CARD = new IdCardNoValidator();

    /** 统一社会信用代码（GB 32100-2015） */
    public static final Validator<String> UNIFIED_SOCIAL_CREDIT_CODE = new UnifiedSocialCreditCodeValidator();

    /** 组织机构代码（GB 11714-1997） */
    public static final Validator<String> ORGANIZATION_CODE = new OrganizationCodeValidator();

    /** 国际电话号码（ITU-T E.164） */
    public static final Validator<String> E164_PHONE = new E164PhoneValidator();

    /** 银行卡号（ISO/IEC 7812） */
    public static final Validator<String> BANK_CARD = new BankCardNoValidator();

    /** 中国大陆手机号（工信部号段表） */
    public static final Validator<String> MOBILE_PHONE = new MobilePhoneValidator();

    /** 邮箱地址 */
    public static final Validator<String> EMAIL = new EmailValidator();

    /** 中国机动车号牌 */
    public static final Validator<String> LICENSE_PLATE = new LicensePlateValidator();

    /** 香港身份证号码 */
    public static final Validator<String> HONG_KONG_ID = new HongKongIdValidator();

    /** 澳门身份证号码 */
    public static final Validator<String> MACAO_ID = new MacaoIdValidator();

    /** 台湾地区身份证号码 */
    public static final Validator<String> TAIWAN_ID = new TaiwanIdValidator();

    private Validators() {
    }
}
