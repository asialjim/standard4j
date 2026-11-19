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
 * 国际电话号码校验器 —— 依据 <b>ITU-T E.164</b>。
 *
 * <p>接受可选的国际冠字 {@code +}，号码为 2~15 位数字且首位不为 {@code 0}。
 * 本校验器只校验号码形态，不校验国家码是否真实分配（国家/地区码见
 * {@code region.CountryOrRegion}）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class E164PhoneValidator implements Validator<String> {

    private static final Pattern PATTERN = Pattern.compile("^\\+?[1-9]\\d{1,14}$");

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("电话号码不能为空");
        if (!PATTERN.matcher(value.trim()).matches())
            return ValidationResult.fail("电话号码必须符合 E.164 格式（可选 + 开头，2~15 位数字，首位不为 0）");
        return ValidationResult.ok();
    }
}
