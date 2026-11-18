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
 * <p>本库只带 {@link #NONE}（原样返回）作为未装配实现时的兜底，
 * 避免因缺少实现导致业务链路中断。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Masker {

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
