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

package com.asialjim.standard4j.sensitive;

/**
 * 可脱敏值契约 —— 任意对象声明"我的原始值是什么、该按哪条规则脱敏"。
 *
 * <p>{@link #maskRule()} 返回 {@code null} 表示该值不需要脱敏。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Maskable {

    /**
     * 原始值（脱敏的输入）
     *
     * @return 原始值，可空
     */
    String rawValue();

    /**
     * 脱敏规则
     *
     * @return 规则；{@code null} 表示不脱敏
     */
    MaskRule maskRule();

    /**
     * 按本对象的规则执行脱敏；规则为 {@code null}、原值为空或执行器不支持时**原样返回**。
     *
     * <p>{@code masker} 为 {@code null} 时使用 {@link Masker#STANDARD}。</p>
     *
     * @param masker 脱敏执行器
     * @return 脱敏后的值
     */
    default String maskedValue(Masker masker) {
        String raw = rawValue();
        MaskRule rule = maskRule();
        if (raw == null || raw.isEmpty() || rule == null)
            return raw;
        Masker actual = masker == null ? Masker.STANDARD : masker;
        return actual.supports(rule) ? actual.mask(raw, rule) : raw;
    }

    /**
     * 使用标准执行器脱敏
     *
     * @return 脱敏后的值
     */
    default String maskedValue() {
        return maskedValue(Masker.STANDARD);
    }
}
