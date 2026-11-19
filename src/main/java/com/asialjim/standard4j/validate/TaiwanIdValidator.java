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

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 台湾地区身份证号码校验器 —— 1 位字母 + 9 位数字（末位为校验位）。
 *
 * <p>字母按户籍地换算为两位数字，再按加权和 MOD 10 校验。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class TaiwanIdValidator implements Validator<String> {

    private static final Pattern PATTERN = Pattern.compile("^[A-Z][1-9]\\d{8}$");

    /** A~Z 对应的两位数字（十位、个位分开使用） */
    private static final int[] LETTER_CODES = {
            10, 11, 12, 13, 14, 15, 16, 17, 34, 18, 19, 20, 21,
            22, 35, 23, 24, 25, 26, 27, 28, 29, 32, 30, 31, 33
    };

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("台湾地区身份证号码不能为空");
        String id = value.trim().toUpperCase(Locale.ROOT);
        if (!PATTERN.matcher(id).matches())
            return ValidationResult.fail("台湾地区身份证号码格式不正确（应为 1 位字母 + 9 位数字）");

        int letterCode = LETTER_CODES[id.charAt(0) - 'A'];
        int sum = letterCode / 10 + (letterCode % 10) * 9;
        int weight = 8;
        for (int i = 1; i <= 8; i++)
            sum += (id.charAt(i) - '0') * weight--;
        sum += id.charAt(9) - '0';
        if (sum % 10 != 0)
            return ValidationResult.fail("台湾地区身份证号码校验位不正确");
        return ValidationResult.ok();
    }
}
