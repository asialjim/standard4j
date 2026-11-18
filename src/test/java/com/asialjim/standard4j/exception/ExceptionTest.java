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

package com.asialjim.standard4j.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 异常体系与错误码行为测试。
 */
class ExceptionTest {

    @Test
    @DisplayName("ResCode 工厂方法：ex() 构建、thr() 抛出")
    void resCodeFactories() {
        assertAll(
                () -> {
                    BusinessException ex = SystemResCode.NOT_FOUND.ex("订单不存在");
                    assertEquals(SystemResCode.NOT_FOUND.code(), ex.code());
                    assertEquals(SystemResCode.NOT_FOUND.status(), ex.status());
                    assertEquals("订单不存在", ex.getMessage());
                    assertSame(SystemResCode.NOT_FOUND, ex.resCode());
                },
                () -> {
                    BusinessException ex = assertThrows(BusinessException.class, SystemResCode.FORBIDDEN::thr);
                    assertEquals(SystemResCode.FORBIDDEN.code(), ex.code());
                    assertEquals("无权访问", ex.getMessage());
                }
        );
    }

    @Test
    @DisplayName("SystemException 与 UnLoginException")
    void specializedExceptions() {
        assertAll(
                () -> {
                    SystemException ex = new SystemException(SystemResCode.INTERNAL_ERROR, "数据库连接失败");
                    assertEquals(SystemResCode.INTERNAL_ERROR.code(), ex.code());
                    assertEquals("数据库连接失败", ex.getMessage());
                },
                () -> {
                    UnLoginException ex = new UnLoginException();
                    assertEquals(SystemResCode.UNAUTHORIZED.code(), ex.code());
                    assertEquals(401, ex.status());
                }
        );
    }
}
