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
 * 脱敏规则标识 —— 规则本身即字典项（实现 {@link Code}），并携带<b>保留位数元数据</b>。
 *
 * <p>本接口只定义"规则长什么样"，<b>不含任何脱敏算法</b>；算法由 {@link Masker} SPI 实现
 * （standard4j 只带 no-op 兜底，真实实现在 sensitive4j）。</p>
 *
 * <p>保留位数语义：</p>
 * <ul>
 *     <li>{@link #sensitiveLeft()} —— 左侧原样保留的字符数；</li>
 *     <li>{@link #sensitiveRight()} —— 右侧原样保留的字符数；</li>
 *     <li>中间部分替换为 {@link #maskChar()}。</li>
 * </ul>
 * <p>示例：身份证号规则 6/4 → {@code 440304********1234}；手机号规则 3/4 → {@code 138****5678}。</p>
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
}
