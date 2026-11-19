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
 * 敏感数据段 —— 原始值中真正需要脱敏的字符区间（左闭右开）。
 *
 * <p>并非所有值都整体敏感：例如邮箱 {@code zhangsan@example.com} 中，
 * {@code @} 与域名是非敏感段，只有 {@code @} 前的本地部分需要脱敏。
 * {@link MaskRule#sensitiveSegment(String)} 返回本对象来声明该区间。</p>
 *
 * @param start 起始下标（含）
 * @param end   结束下标（不含）
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public record SensitiveSegment(int start, int end) {

    public SensitiveSegment {
        if (start < 0)
            throw new IllegalArgumentException("敏感段起始下标不能为负数");
        if (end < start)
            throw new IllegalArgumentException("敏感段结束下标不能小于起始下标");
    }

    /**
     * 整个值均为敏感段
     *
     * @param rawValue 原始值
     * @return {@link SensitiveSegment}
     */
    public static SensitiveSegment whole(String rawValue) {
        return new SensitiveSegment(0, rawValue == null ? 0 : rawValue.length());
    }

    /**
     * 取最后一个分隔符之前的区间；分隔符不存在时整段敏感
     *
     * <p>适用于邮箱本地部分（分隔符 {@code '@'}）等场景。</p>
     *
     * @param rawValue  原始值
     * @param delimiter 分隔符
     * @return {@link SensitiveSegment}
     */
    public static SensitiveSegment beforeLast(String rawValue, char delimiter) {
        if (rawValue == null)
            return new SensitiveSegment(0, 0);
        int index = rawValue.lastIndexOf(delimiter);
        return new SensitiveSegment(0, index < 0 ? rawValue.length() : index);
    }

    /**
     * 敏感段长度
     *
     * @return 长度
     */
    public int length() {
        return this.end - this.start;
    }

    /**
     * 是否为空段
     *
     * @return 长度为 0 返回 {@code true}
     */
    public boolean empty() {
        return this.end <= this.start;
    }
}
