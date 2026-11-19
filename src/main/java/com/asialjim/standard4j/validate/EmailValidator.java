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

import java.util.regex.Pattern;

/**
 * 邮箱地址校验器 —— 常用 RFC 5322 简化口径。
 *
 * <p>校验内容：总长度不超过 254、本地部分不超过 64、单个 {@code @}、域名含点且
 * 顶级域为 2~63 位字母。不校验 DNS 是否存在，不支持国际化域名（IDN）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class EmailValidator implements Validator<String> {

    private static final Pattern PATTERN = Pattern.compile(
            "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
                    + "@(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+[A-Za-z]{2,63}$");

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("邮箱地址不能为空");
        String email = value.trim();
        if (email.length() > 254)
            return ValidationResult.fail("邮箱地址长度不能超过 254 个字符");
        int at = email.indexOf('@');
        if (at <= 0 || at != email.lastIndexOf('@'))
            return ValidationResult.fail("邮箱地址必须包含且仅包含一个 @，且本地部分不能为空");
        if (at > 64)
            return ValidationResult.fail("邮箱地址本地部分不能超过 64 个字符");
        if (!PATTERN.matcher(email).matches())
            return ValidationResult.fail("邮箱地址格式不正确");
        return ValidationResult.ok();
    }
}
