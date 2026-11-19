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
 * 银行卡号校验器 —— 依据 <b>ISO/IEC 7812</b>。
 *
 * <p>校验内容：去掉空格与连字符后为 12~19 位数字、首位不为 {@code 0}、Luhn 校验通过。
 * BIN（发卡行标识）段只校验位长，不校验发卡行是否存在（BIN 表不在本库内置）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class BankCardNoValidator implements Validator<String> {

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("银行卡号不能为空");
        String card = value.trim().replace(" ", "").replace("-", "");
        if (card.length() < 12 || card.length() > 19)
            return ValidationResult.fail("银行卡号必须为 12~19 位数字");
        for (int i = 0; i < card.length(); i++) {
            if (!Character.isDigit(card.charAt(i)))
                return ValidationResult.fail("银行卡号只能包含数字");
        }
        if (card.charAt(0) == '0')
            return ValidationResult.fail("银行卡号首位不能为 0");

        int sum = 0;
        boolean doubleDigit = false;
        for (int i = card.length() - 1; i >= 0; i--) {
            int digit = card.charAt(i) - '0';
            if (doubleDigit) {
                digit *= 2;
                if (digit > 9)
                    digit -= 9;
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }
        if (sum % 10 != 0)
            return ValidationResult.fail("银行卡号 Luhn 校验不通过");
        return ValidationResult.ok();
    }
}
