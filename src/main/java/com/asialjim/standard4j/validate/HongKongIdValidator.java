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
 * 香港身份证号码校验器 —— 香港入境事务处格式与校验位规则。
 *
 * <p>格式为 1~2 位字母 + 6 位数字 + 1 位校验位（数字或 {@code A}），
 * 校验位可带括号，如 {@code A123456(8)}。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class HongKongIdValidator implements Validator<String> {

    private static final Pattern PATTERN = Pattern.compile(
            "^[A-Z]{1,2}\\d{6}(?:\\([0-9A]\\)|[0-9A])$");

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("香港身份证号码不能为空");
        String id = value.trim().toUpperCase(Locale.ROOT);
        if (!PATTERN.matcher(id).matches())
            return ValidationResult.fail("香港身份证号码格式不正确（应为 1~2 位字母 + 6 位数字 + 校验位）");
        String compact = id.replace("(", "").replace(")", "");
        int letterCount = Character.isLetter(compact.charAt(1)) ? 2 : 1;
        int sum = 0;
        if (letterCount == 2) {
            sum += (compact.charAt(0) - 'A' + 10) * 9;
            sum += (compact.charAt(1) - 'A' + 10) * 8;
        } else {
            sum += (compact.charAt(0) - 'A' + 10) * 8;
        }
        int digitStart = letterCount;
        int weight = 7;
        for (int i = digitStart; i < digitStart + 6; i++)
            sum += (compact.charAt(i) - '0') * weight--;
        int check = (11 - sum % 11) % 11;
        char expected = check == 10 ? 'A' : (char) ('0' + check);
        if (expected != compact.charAt(compact.length() - 1))
            return ValidationResult.fail("香港身份证号码校验位不正确");
        return ValidationResult.ok();
    }
}
