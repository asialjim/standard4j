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
 * 脱敏执行器 SPI —— 由实现库（sensitive4j）或使用方提供具体算法。
 *
 * <p>本库内置两个实现：</p>
 * <ul>
 *     <li>{@link #STANDARD}：按 {@link MaskRule#maskedValue(String)} 的默认算法执行
 *         （先定位敏感段，再段内左右保留、中间掩码）；</li>
 *     <li>{@link #NONE}：未装配实现时的 no-op 兜底，原样返回，避免业务链路中断。</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Masker {

    /**
     * 标准实现：委托规则自身的默认算法
     */
    Masker STANDARD = new Masker() {
        @Override
        public boolean supports(MaskRule rule) {
            return rule != null;
        }

        @Override
        public String mask(String rawValue, MaskRule rule) {
            return rule == null ? rawValue : rule.maskedValue(rawValue);
        }
    };

    /**
     * no-op 兜底实现：始终"支持"，但原样返回
     */
    Masker NONE = new Masker() {
        @Override
        public boolean supports(MaskRule rule) {
            return true;
        }

        @Override
        public String mask(String rawValue, MaskRule rule) {
            return rawValue;
        }
    };

    /**
     * 是否支持该规则
     *
     * @param rule 脱敏规则
     * @return 支持返回 {@code true}
     */
    boolean supports(MaskRule rule);

    /**
     * 执行脱敏
     *
     * @param rawValue 原始值（可空）
     * @param rule     脱敏规则
     * @return 脱敏后的值（原值为空时建议原样返回空）
     */
    String mask(String rawValue, MaskRule rule);
}
