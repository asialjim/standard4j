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
 * 中国大陆手机号校验器 —— 依据工信部现行号段表。
 *
 * <p>校验 11 位手机号及号段合法性；不判断运营商归属（携号转网后号段归属不稳定，
 * 运营商字典不在本库内置）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class MobilePhoneValidator implements Validator<String> {

    private static final Pattern PATTERN = Pattern.compile(
            "^1(?:3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9\\d)\\d{8}$");

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("手机号不能为空");
        if (!PATTERN.matcher(value.trim()).matches())
            return ValidationResult.fail("手机号格式不正确（应为 11 位中国大陆手机号）");
        return ValidationResult.ok();
    }
}
