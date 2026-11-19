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
 * 澳门身份证号码校验器 —— 格式口径。
 *
 * <p>澳门居民身份证号通常为 7 位数字，可带 1 位校验位（常见写法为括号包裹）。
 * 校验位算法未公开统一，本校验器只校验形态，不做校验和计算。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class MacaoIdValidator implements Validator<String> {

    private static final Pattern PATTERN = Pattern.compile("^[1-9]\\d{6}(?:\\([0-9]\\)|[0-9])?$");

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("澳门身份证号码不能为空");
        if (!PATTERN.matcher(value.trim()).matches())
            return ValidationResult.fail("澳门身份证号码格式不正确（应为 7 位数字，可带 1 位校验位）");
        return ValidationResult.ok();
    }
}
