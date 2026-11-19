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
 * 组织机构代码校验器 —— 依据 <b>GB 11714-1997</b>。
 *
 * <p>校验内容：旧版 9 位代码（8 位本体 + 1 位校验码），本体字符集为数字与大写字母
 * （不含 {@code I/O/S/V/Z}），校验位为 {@code 0-9} 或 {@code X}，算法为 MOD 11。</p>
 *
 * <p>统一社会信用代码已逐步取代组织机构代码；本校验器用于历史数据兼容。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class OrganizationCodeValidator implements Validator<String> {

    private static final String CHARSET = "0123456789ABCDEFGHJKLMNPQRTUWXY";
    private static final int[] WEIGHTS = {3, 7, 9, 10, 5, 8, 4, 2};

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("组织机构代码不能为空");
        String code = value.trim().toUpperCase(Locale.ROOT);
        if (code.length() != 9)
            return ValidationResult.fail("组织机构代码必须为 9 位");
        for (int i = 0; i < 8; i++) {
            if (CHARSET.indexOf(code.charAt(i)) < 0)
                return ValidationResult.fail("组织机构代码本体只能包含数字和大写字母（不含 I/O/S/V/Z）");
        }
        char last = code.charAt(8);
        if (!Character.isDigit(last) && last != 'X')
            return ValidationResult.fail("组织机构代码校验位只能为数字或 X");

        int sum = 0;
        for (int i = 0; i < 8; i++) {
            char c = code.charAt(i);
            int digitValue = Character.isDigit(c) ? c - '0' : c - 'A' + 10;
            sum += digitValue * WEIGHTS[i];
        }
        int check = 11 - sum % 11;
        char expected;
        if (check == 10)
            expected = 'X';
        else if (check == 11)
            expected = '0';
        else
            expected = (char) ('0' + check);
        if (expected != last)
            return ValidationResult.fail("组织机构代码校验位不正确");
        return ValidationResult.ok();
    }
}
