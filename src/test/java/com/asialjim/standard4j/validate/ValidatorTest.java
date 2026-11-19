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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 内置校验器测试：国家标准与行业标准规则。
 */
class ValidatorTest {

    @Test
    @DisplayName("公民身份号码：GB 11643-1999 MOD 11-2、出生日期段")
    void idCard() {
        assertAll(
                () -> assertTrue(Validators.ID_CARD.isValid("11010519491231002X")),
                () -> assertTrue(Validators.ID_CARD.isValid("11010519491231002x")),
                () -> assertFalse(Validators.ID_CARD.isValid("110105194912310021")),
                () -> assertFalse(Validators.ID_CARD.isValid("11010519491332002X")),
                () -> assertFalse(Validators.ID_CARD.isValid("1101051949123100")),
                () -> assertFalse(Validators.ID_CARD.isValid(null))
        );
    }

    @Test
    @DisplayName("统一社会信用代码：GB 32100-2015 MOD 31")
    void unifiedSocialCreditCode() {
        assertAll(
                () -> assertTrue(Validators.UNIFIED_SOCIAL_CREDIT_CODE.isValid("91350100M000100Y43")),
                () -> assertTrue(Validators.UNIFIED_SOCIAL_CREDIT_CODE.isValid("91350100m000100y43")),
                () -> assertFalse(Validators.UNIFIED_SOCIAL_CREDIT_CODE.isValid("91350100M000100Y44")),
                () -> assertFalse(Validators.UNIFIED_SOCIAL_CREDIT_CODE.isValid("91350100M000100YI3")),
                () -> assertFalse(Validators.UNIFIED_SOCIAL_CREDIT_CODE.isValid("91350100M000100Y4"))
        );
    }

    @Test
    @DisplayName("组织机构代码：GB 11714-1997 MOD 11")
    void organizationCode() {
        assertAll(
                () -> assertTrue(Validators.ORGANIZATION_CODE.isValid("123456788")),
                () -> assertTrue(Validators.ORGANIZATION_CODE.isValid("110105006")),
                () -> assertFalse(Validators.ORGANIZATION_CODE.isValid("123456789")),
                () -> assertFalse(Validators.ORGANIZATION_CODE.isValid("12345678")),
                () -> assertFalse(Validators.ORGANIZATION_CODE.isValid("1234567I8"))
        );
    }

    @Test
    @DisplayName("国际电话号码：ITU-T E.164")
    void e164() {
        assertAll(
                () -> assertTrue(Validators.E164_PHONE.isValid("+8613800138000")),
                () -> assertTrue(Validators.E164_PHONE.isValid("8613800138000")),
                () -> assertFalse(Validators.E164_PHONE.isValid("+0123456789")),
                () -> assertFalse(Validators.E164_PHONE.isValid("+1234567890123456")),
                () -> assertFalse(Validators.E164_PHONE.isValid("abc"))
        );
    }

    @Test
    @DisplayName("银行卡号：ISO/IEC 7812 Luhn")
    void bankCard() {
        assertAll(
                () -> assertTrue(Validators.BANK_CARD.isValid("4111111111111111")),
                () -> assertTrue(Validators.BANK_CARD.isValid("6222021234567890128")),
                () -> assertTrue(Validators.BANK_CARD.isValid("4111 1111 1111 1111")),
                () -> assertFalse(Validators.BANK_CARD.isValid("4111111111111112")),
                () -> assertFalse(Validators.BANK_CARD.isValid("1234")),
                () -> assertFalse(Validators.BANK_CARD.isValid("0411111111111111"))
        );
    }

    @Test
    @DisplayName("中国大陆手机号：11 位与号段")
    void mobilePhone() {
        assertAll(
                () -> assertTrue(Validators.MOBILE_PHONE.isValid("13800138000")),
                () -> assertTrue(Validators.MOBILE_PHONE.isValid("19912345678")),
                () -> assertTrue(Validators.MOBILE_PHONE.isValid("14512345678")),
                () -> assertTrue(Validators.MOBILE_PHONE.isValid("17012345678")),
                () -> assertFalse(Validators.MOBILE_PHONE.isValid("15412345678")),
                () -> assertFalse(Validators.MOBILE_PHONE.isValid("1380013800")),
                () -> assertFalse(Validators.MOBILE_PHONE.isValid("23800138000"))
        );
    }

    @Test
    @DisplayName("邮箱地址：格式与长度")
    void email() {
        assertAll(
                () -> assertTrue(Validators.EMAIL.isValid("user@example.com")),
                () -> assertTrue(Validators.EMAIL.isValid("a.b+c@sub.example.co")),
                () -> assertFalse(Validators.EMAIL.isValid("user@")),
                () -> assertFalse(Validators.EMAIL.isValid("@example.com")),
                () -> assertFalse(Validators.EMAIL.isValid("user@@example.com")),
                () -> assertFalse(Validators.EMAIL.isValid("user@example")),
                () -> assertFalse(Validators.EMAIL.isValid("user @example.com"))
        );
    }

    @Test
    @DisplayName("中国机动车号牌：普通 7 位与新能源 8 位")
    void licensePlate() {
        assertAll(
                () -> assertTrue(Validators.LICENSE_PLATE.isValid("京A12345")),
                () -> assertTrue(Validators.LICENSE_PLATE.isValid("粤B12345")),
                () -> assertTrue(Validators.LICENSE_PLATE.isValid("京AD12345")),
                () -> assertTrue(Validators.LICENSE_PLATE.isValid("沪A12345学")),
                () -> assertTrue(Validators.LICENSE_PLATE.isValid("使123456")),
                () -> assertFalse(Validators.LICENSE_PLATE.isValid("AB12345")),
                () -> assertFalse(Validators.LICENSE_PLATE.isValid("京I12345")),
                () -> assertFalse(Validators.LICENSE_PLATE.isValid("京A1234"))
        );
    }

    @Test
    @DisplayName("香港身份证号码：格式与 MOD 11 校验位")
    void hongKongId() {
        assertAll(
                () -> assertTrue(Validators.HONG_KONG_ID.isValid("A123456(8)")),
                () -> assertTrue(Validators.HONG_KONG_ID.isValid("A1234568")),
                () -> assertTrue(Validators.HONG_KONG_ID.isValid("AB123456(9)")),
                () -> assertFalse(Validators.HONG_KONG_ID.isValid("A123456(7)")),
                () -> assertFalse(Validators.HONG_KONG_ID.isValid("A12345"))
        );
    }

    @Test
    @DisplayName("澳门身份证号码：形态校验")
    void macaoId() {
        assertAll(
                () -> assertTrue(Validators.MACAO_ID.isValid("1234567")),
                () -> assertTrue(Validators.MACAO_ID.isValid("1234567(8)")),
                () -> assertTrue(Validators.MACAO_ID.isValid("12345678")),
                () -> assertFalse(Validators.MACAO_ID.isValid("0123456")),
                () -> assertFalse(Validators.MACAO_ID.isValid("123456"))
        );
    }

    @Test
    @DisplayName("台湾地区身份证号码：字母换算与 MOD 10")
    void taiwanId() {
        assertAll(
                () -> assertTrue(Validators.TAIWAN_ID.isValid("A123456789")),
                () -> assertFalse(Validators.TAIWAN_ID.isValid("A123456788")),
                () -> assertFalse(Validators.TAIWAN_ID.isValid("B123456789")),
                () -> assertFalse(Validators.TAIWAN_ID.isValid("A12345678"))
        );
    }
}
