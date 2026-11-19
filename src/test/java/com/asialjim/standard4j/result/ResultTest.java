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

import com.asialjim.standard4j.exception.SimpleResCode;
import com.asialjim.standard4j.exception.SystemResCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 统一响应体与分页元数据测试。
 */
class ResultTest {

    @Test
    @DisplayName("PageMeta：总页数推导、空页与下一页判定")
    void pageMeta() {
        PageMeta page = PageMeta.of(101, 10, 2);
        assertAll(
                () -> assertEquals(11, page.totalPages()),
                () -> assertEquals(101, page.totalRecords()),
                () -> assertFalse(page.empty()),
                () -> assertTrue(page.hasNext()),
                () -> assertEquals(0, PageMeta.of(0, 10, 1).totalPages()),
                () -> assertTrue(PageMeta.of(0, 10, 1).empty()),
                () -> assertFalse(PageMeta.of(100, 10, 10).hasNext()),
                () -> assertThrows(IllegalArgumentException.class, () -> PageMeta.of(10, 0, 1))
        );
    }

    @Test
    @DisplayName("Result：成功、分页成功与失败")
    void resultFactories() {
        PageMeta page = PageMeta.of(3, 2, 1);
        Result<List<String>> paged = Result.page(List.of("a", "b"), page);
        assertAll(
                () -> assertEquals(Result.SUCCESS_CODE, Result.ok("x").code()),
                () -> assertEquals("x", Result.ok("x").data()),
                () -> assertTrue(Result.ok("x").success()),
                () -> assertEquals(200, Result.ok("x").status()),
                () -> assertFalse(Result.ok("x").paged()),
                () -> assertTrue(paged.paged()),
                () -> assertEquals(page, paged.page()),
                () -> assertEquals(1, paged.currentPage()),
                () -> assertEquals(2, paged.pageSize()),
                () -> assertEquals(2, paged.totalPages()),
                () -> assertEquals(3, paged.totalRecords()),
                () -> assertEquals(0, Result.ok("x").currentPage()),
                () -> assertEquals(List.of("a", "b"), paged.data())
        );
    }

    @Test
    @DisplayName("Result：失败响应沿用 ResCode 的码值与状态码")
    void failure() {
        Result<String> system = Result.fail(SystemResCode.INTERNAL_ERROR);
        Result<String> dynamic = Result.fail(SimpleResCode.of("BIZ-001", "参数错误", 422));
        assertAll(
                () -> assertFalse(system.success()),
                () -> assertEquals(500, system.status()),
                () -> assertEquals(SystemResCode.INTERNAL_ERROR.code(), system.code()),
                () -> assertEquals("系统内部错误", system.message()),
                () -> assertEquals(422, dynamic.status()),
                () -> assertEquals("BIZ-001", dynamic.code()),
                () -> assertEquals("参数错误", dynamic.message()),
                () -> assertFalse(Result.fail(SystemResCode.SYSTEM_BUSY, "稍后重试").message().isEmpty())
        );
    }

    @Test
    @DisplayName("SimpleResCode：动态码值与状态码兜底")
    void simpleResCode() {
        assertAll(
                () -> assertEquals(400, SimpleResCode.of("X", "x").status()),
                () -> assertEquals(400, SimpleResCode.of("X", "x", 0).status()),
                () -> assertEquals("X", SimpleResCode.of("X", "x").code()),
                () -> assertThrows(NullPointerException.class, () -> SimpleResCode.of(null, "x"))
        );
    }
}
