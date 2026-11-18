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

package com.asialjim.standard4j.code;

import com.asialjim.standard4j.exception.BusinessException;
import com.asialjim.standard4j.exception.SystemResCode;

import java.util.Optional;

/**
 * 字典仓库接口 —— 为<b>非枚举实现</b>的字典（文件驱动、数据库字典表等）提供与枚举
 * {@code of()/ofNullable()/ofOrDefault()} 同口径的查询契约。
 *
 * <p>背景：小字典用封闭枚举即可获得静态查询能力；行政区划、行业等大数据量字典
 * 由使用方自行实现，本接口约束其查询口径与枚举一致：</p>
 * <ul>
 *     <li>{@link #ofNullable(String)}：宽容查找，未命中返回空 {@link Optional}（实现方必须提供）；</li>
 *     <li>{@link #of(String)}：严格查找，未命中抛 {@link BusinessException}（{@link SystemResCode#UNMATCHED_CODE}）；</li>
 *     <li>{@link #ofOrDefault(String)}：宽容解析，未命中落 {@link #unknown()}。</li>
 * </ul>
 *
 * <p>实现建议：枚举字典可顺手提供本接口实现（内部委托 {@link Codes}），
 * 使"枚举还是文件驱动"对调用方无感。</p>
 *
 * @param <T> 字典项类型
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface CodeRepository<T extends Code> {

    /**
     * 该字典的 UNKNOWN 项（宽容解析的兜底值）
     *
     * @return UNKNOWN 项，不允许为 {@code null}
     */
    T unknown();

    /**
     * 宽容查找：按码值匹配（实现方建议与 {@link Codes} 同口径：不区分大小写、容忍首尾空白）
     *
     * @param code 码值
     * @return 命中的字典项，未命中返回 {@link Optional#empty()}
     */
    Optional<T> ofNullable(String code);

    /**
     * 严格查找：未命中抛 {@link BusinessException}（{@link SystemResCode#UNMATCHED_CODE}）
     *
     * @param code 码值
     * @return 命中的字典项
     */
    default T of(String code) {
        return this.ofNullable(code)
                .orElseThrow(() -> new BusinessException(SystemResCode.UNMATCHED_CODE, "未知的字典码值: [" + code + "]"));
    }

    /**
     * 宽容解析：未命中/无效入参一律落 {@link #unknown()}
     *
     * @param code 码值
     * @return 字典项，未命中返回 {@link #unknown()}
     */
    default T ofOrDefault(String code) {
        return this.ofNullable(code).orElse(this.unknown());
    }
}
