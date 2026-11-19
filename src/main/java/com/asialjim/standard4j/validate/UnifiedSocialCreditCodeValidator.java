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

/**
 * 统一社会信用代码校验器 —— 依据 <b>GB 32100-2015</b>。
 *
 * <p>校验内容：18 位长度、字符集（数字与大写字母，不含 {@code I/O/S/V/Z}）、
 * 第 18 位校验码（MOD 31）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class UnifiedSocialCreditCodeValidator implements Validator<String> {

    /** GB 32100 允许的 31 个字符（去掉了易混淆的 I/O/S/V/Z） */
    private static final String CHARSET = "0123456789ABCDEFGHJKLMNPQRTUWXY";
    private static final int[] WEIGHTS = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("统一社会信用代码不能为空");
        String code = value.trim().toUpperCase(Locale.ROOT);
        if (code.length() != 18)
            return ValidationResult.fail("统一社会信用代码必须为 18 位");
        for (int i = 0; i < code.length(); i++) {
            if (CHARSET.indexOf(code.charAt(i)) < 0)
                return ValidationResult.fail("统一社会信用代码只能包含数字和大写字母（不含 I/O/S/V/Z）");
        }
        int sum = 0;
        for (int i = 0; i < 17; i++)
            sum += CHARSET.indexOf(code.charAt(i)) * WEIGHTS[i];
        int checkIndex = (31 - sum % 31) % 31;
        if (CHARSET.charAt(checkIndex) != code.charAt(17))
            return ValidationResult.fail("统一社会信用代码校验位不正确");
        return ValidationResult.ok();
    }
}
