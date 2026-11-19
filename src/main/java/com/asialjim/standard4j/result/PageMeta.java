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

package com.asialjim.standard4j.result;

/**
 * 分页结果元数据 —— 只描述响应侧的分页结构，不包含分页请求参数。
 *
 * @param currentPage  当前页（从 1 开始）
 * @param pageSize     当前页大小
 * @param totalPages   总页数
 * @param totalRecords 总记录数
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public record PageMeta(int currentPage, int pageSize, int totalPages, long totalRecords) {

    public PageMeta {
        if (currentPage < 1)
            throw new IllegalArgumentException("currentPage 必须大于等于 1");
        if (pageSize < 1)
            throw new IllegalArgumentException("pageSize 必须大于等于 1");
        if (totalPages < 0)
            throw new IllegalArgumentException("totalPages 不能为负数");
        if (totalRecords < 0)
            throw new IllegalArgumentException("totalRecords 不能为负数");
    }

    /**
     * 根据总记录数、页大小和当前页推导总页数
     *
     * @param totalRecords 总记录数
     * @param pageSize     页大小
     * @param currentPage  当前页（从 1 开始）
     * @return {@link PageMeta}
     */
    public static PageMeta of(long totalRecords, int pageSize, int currentPage) {
        if (totalRecords < 0)
            throw new IllegalArgumentException("totalRecords 不能为负数");
        if (pageSize < 1)
            throw new IllegalArgumentException("pageSize 必须大于等于 1");
        long pages = totalRecords == 0 ? 0 : (totalRecords + pageSize - 1) / pageSize;
        int totalPages = pages > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) pages;
        return new PageMeta(currentPage, pageSize, totalPages, totalRecords);
    }

    /**
     * 是否为空页
     *
     * @return 总记录数为 0 时返回 {@code true}
     */
    public boolean empty() {
        return this.totalRecords == 0;
    }

    /**
     * 是否存在下一页
     *
     * @return 存在下一页返回 {@code true}
     */
    public boolean hasNext() {
        return this.currentPage < this.totalPages;
    }
}
