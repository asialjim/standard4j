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

package com.asialjim.standard4j.entity;

import java.time.LocalDateTime;

/**
 * 审计字段契约：创建人/创建时间/更新人/更新时间。
 *
 * <p>字段名统一为 {@code createdBy/createdAt/updatedBy/updatedAt}，杜绝各项目各写一套
 * （{@code createTime}/{@code gmtCreate}/{@code insertTime} 等）。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Auditable {

    /**
     * 创建人（用户标识，可空）
     *
     * @return 创建人
     */
    String getCreatedBy();

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    void setCreatedBy(String createdBy);

    /**
     * 创建时间
     *
     * @return 创建时间
     */
    LocalDateTime getCreatedAt();

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    void setCreatedAt(LocalDateTime createdAt);

    /**
     * 最后更新人（用户标识，可空）
     *
     * @return 更新人
     */
    String getUpdatedBy();

    /**
     * 设置最后更新人
     *
     * @param updatedBy 更新人
     */
    void setUpdatedBy(String updatedBy);

    /**
     * 最后更新时间
     *
     * @return 更新时间
     */
    LocalDateTime getUpdatedAt();

    /**
     * 设置最后更新时间
     *
     * @param updatedAt 更新时间
     */
    void setUpdatedAt(LocalDateTime updatedAt);
}
