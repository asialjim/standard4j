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

import java.util.List;

/**
 * 层级字典仓库接口 —— 在 {@link CodeRepository} 之上补充层级查询契约。
 *
 * <p>适用于行政区划、行业、职业等层级编码标准的仓库实现。</p>
 *
 * @param <T> 层级字典项类型
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface HierarchicalCodeRepository<T extends HierarchicalCode> extends CodeRepository<T> {

    /**
     * 查询指定项的直接上级
     *
     * @param code 码值
     * @return 上级项；顶级项或未命中返回 {@link Optional#empty()}
     */
    default java.util.Optional<T> parentOf(String code) {
        return this.ofNullable(code)
                .flatMap(item -> item.parentCode() == null ? java.util.Optional.empty() : this.ofNullable(item.parentCode()));
    }

    /**
     * 查询指定项的直接下级
     *
     * @param parentCode 上级码值
     * @return 下级项列表（含顺序），无下级返回空列表
     */
    List<T> childrenOf(String parentCode);
}
