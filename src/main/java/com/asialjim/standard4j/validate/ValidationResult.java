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
 * 校验结果 —— 通过/不通过 + 不通过原因。
 *
 * @param valid   是否通过
 * @param message 不通过原因；通过时为 {@code null}
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public record ValidationResult(boolean valid, String message) {

    private static final ValidationResult OK = new ValidationResult(true, null);

    /**
     * 通过
     *
     * @return 通过的校验结果
     */
    public static ValidationResult ok() {
        return OK;
    }

    /**
     * 不通过
     *
     * @param message 不通过原因
     * @return 不通过的校验结果
     */
    public static ValidationResult fail(String message) {
        return new ValidationResult(false, message);
    }

    /**
     * 是否不通过
     *
     * @return 不通过返回 {@code true}
     */
    public boolean invalid() {
        return !this.valid;
    }
}
