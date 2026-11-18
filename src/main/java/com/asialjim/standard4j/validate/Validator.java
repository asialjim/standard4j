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
 * 统一校验器接口 —— <b>只判断与给原因，不做脱敏、不做格式化</b>。
 *
 * <p>失败原因面向人类阅读，不含错误码；错误码由业务层按 {@code ResCode} 自行定义。</p>
 *
 * @param <T> 被校验的值类型
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * 校验
     *
     * @param value 被校验的值，可空
     * @return {@link ValidationResult}
     */
    ValidationResult validate(T value);

    /**
     * 是否通过校验
     *
     * @param value 被校验的值
     * @return 通过返回 {@code true}
     */
    default boolean isValid(T value) {
        return validate(value).valid();
    }
}
