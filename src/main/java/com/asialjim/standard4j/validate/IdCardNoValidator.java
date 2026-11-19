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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * 公民身份号码校验器 —— 依据 <b>GB 11643-1999</b>。
 *
 * <p>校验内容：18 位长度、前 17 位数字、出生日期段合法且不晚于今天、
 * MOD 11-2 校验码（末位 {@code 0-9} 或 {@code X}）。不校验行政区划码是否真实存在
 * （区划数据不在本库内置）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class IdCardNoValidator implements Validator<String> {

    private static final int[] WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final char[] CHECK_CODES = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    private static final DateTimeFormatter BIRTH_FORMAT = DateTimeFormatter.BASIC_ISO_DATE;

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("公民身份号码不能为空");
        String id = value.trim().toUpperCase(Locale.ROOT);
        if (id.length() != 18)
            return ValidationResult.fail("公民身份号码必须为 18 位");
        for (int i = 0; i < 17; i++) {
            if (!Character.isDigit(id.charAt(i)))
                return ValidationResult.fail("公民身份号码前 17 位必须为数字");
        }
        char last = id.charAt(17);
        if (!Character.isDigit(last) && last != 'X')
            return ValidationResult.fail("公民身份号码校验位只能为数字或 X");
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(id.substring(6, 14), BIRTH_FORMAT);
        } catch (DateTimeParseException e) {
            return ValidationResult.fail("公民身份号码中的出生日期段不合法");
        }
        if (birthday.isAfter(LocalDate.now()))
            return ValidationResult.fail("公民身份号码中的出生日期不能晚于今天");

        int sum = 0;
        for (int i = 0; i < 17; i++)
            sum += (id.charAt(i) - '0') * WEIGHTS[i];
        char expected = CHECK_CODES[sum % 11];
        if (expected != last)
            return ValidationResult.fail("公民身份号码校验位不正确");
        return ValidationResult.ok();
    }
}
