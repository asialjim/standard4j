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

import com.asialjim.standard4j.code.Code;

/**
 * 脱敏规则标识 —— 规则本身即字典项（实现 {@link Code}），并携带<b>脱敏元数据与默认算法</b>。
 *
 * <p>脱敏分两步：</p>
 * <ol>
 *     <li><b>定位敏感数据段</b>：{@link #sensitiveSegment(String)} 返回原始值中真正敏感
 *         的字符区间；非敏感段（如邮箱的 {@code @domain}）原样保留；</li>
 *     <li><b>段内掩码</b>：敏感段左侧保留 {@link #sensitiveLeft()} 个字符、右侧保留
 *         {@link #sensitiveRight()} 个字符，中间全部替换为 {@link #maskChar()}。</li>
 * </ol>
 *
 * <p>示例：身份证号规则 6/4 → {@code 440304********1234}；手机号规则 3/4 →
 * {@code 138****5678}；邮箱规则 1/0 → {@code z*******@example.com}。</p>
 *
 * <p>{@link #maskedValue(String)} 提供默认实现；需要特殊算法时由 {@link Masker} SPI
 * 覆盖（实现库可放在 sensitive4j）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface MaskRule extends Code {

    /**
     * 左侧保留字符数
     *
     * @return 保留位数（{@code >= 0}）
     */
    int sensitiveLeft();

    /**
     * 右侧保留字符数
     *
     * @return 保留位数（{@code >= 0}）
     */
    int sensitiveRight();

    /**
     * 掩码字符
     *
     * @return 默认 {@code '*'}
     */
    default char maskChar() {
        return '*';
    }

    /**
     * 定位敏感数据段
     *
     * <p>默认整个值均为敏感段；邮箱等"部分敏感"场景覆盖本方法，例如返回
     * {@code @} 之前的区间。</p>
     *
     * @param rawValue 原始值，可空
     * @return {@link SensitiveSegment}；返回空段表示不脱敏
     */
    default SensitiveSegment sensitiveSegment(String rawValue) {
        return SensitiveSegment.whole(rawValue);
    }

    /**
     * 按本规则执行默认脱敏：仅替换敏感段，敏感段外原样保留。
     *
     * <p>为保证不泄露，敏感段长度不足时优先保证至少掩码 1 个字符：
     * 例如长度 2、规则 1/1 时结果为 {@code a*}。</p>
     *
     * @param rawValue 原始值，可空
     * @return 脱敏后的值；入参为 {@code null}/空串时原样返回
     */
    default String maskedValue(String rawValue) {
        if (rawValue == null || rawValue.isEmpty())
            return rawValue;
        SensitiveSegment segment = this.sensitiveSegment(rawValue);
        if (segment == null || segment.empty())
            return rawValue;
        int start = Math.min(segment.start(), rawValue.length());
        int end = Math.min(segment.end(), rawValue.length());
        int length = end - start;
        if (length <= 0)
            return rawValue;

        int left = Math.max(0, Math.min(this.sensitiveLeft(), length - 1));
        int right = Math.max(0, Math.min(this.sensitiveRight(), length - left - 1));
        StringBuilder masked = new StringBuilder(rawValue);
        for (int i = start + left; i < end - right; i++)
            masked.setCharAt(i, this.maskChar());
        return masked.toString();
    }
}
