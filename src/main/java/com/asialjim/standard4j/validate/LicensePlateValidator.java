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
 * 中国机动车号牌校验器 —— 常用民用号牌口径。
 *
 * <p>支持普通号牌（7 位）与新能源号牌（8 位）。校验内容：省份简称、发牌机关字母、
 * 序号长度与字符集。不校验证件与车辆的真实登记关系。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public final class LicensePlateValidator implements Validator<String> {

    private static final Pattern PATTERN = Pattern.compile(
            "^(?:[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]"
                    + "[A-HJ-NP-Z][A-HJ-NP-Z0-9]{4,5}[A-HJ-NP-Z0-9挂学警港澳]|[使领]\\d{6})$");

    @Override
    public ValidationResult validate(String value) {
        if (value == null || value.isBlank())
            return ValidationResult.fail("车牌号不能为空");
        String plate = value.trim().toUpperCase(Locale.ROOT);
        if (!PATTERN.matcher(plate).matches())
            return ValidationResult.fail("车牌号格式不正确");
        return ValidationResult.ok();
    }
}
