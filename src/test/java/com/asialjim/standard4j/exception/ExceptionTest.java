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
                    BusinessException ex = SystemResCode.UNMATCHED_CODE.ex("未知的字典码值: [99]");
                    assertEquals(SystemResCode.UNMATCHED_CODE.code(), ex.code());
                    assertEquals(SystemResCode.UNMATCHED_CODE.status(), ex.status());
                    assertEquals("未知的字典码值: [99]", ex.getMessage());
                    assertSame(SystemResCode.UNMATCHED_CODE, ex.resCode());
                },
                () -> {
                    BusinessException ex = assertThrows(BusinessException.class, SystemResCode.SYSTEM_BUSY::thr);
                    assertEquals(SystemResCode.SYSTEM_BUSY.code(), ex.code());
                    assertEquals("系统繁忙", ex.getMessage());
                }
        );
    }

    @Test
    @DisplayName("SystemException：直接继承 RuntimeException")
    void specializedExceptions() {
        assertAll(
                () -> {
                    SystemException ex = new SystemException(SystemResCode.INTERNAL_ERROR, "数据库连接失败");
                    assertEquals(SystemResCode.INTERNAL_ERROR.code(), ex.code());
                    assertEquals("数据库连接失败", ex.getMessage());
                },
                () -> {
                    // SystemException 与 BusinessException 平级，直接继承 RuntimeException
                    assertEquals(RuntimeException.class, SystemException.class.getSuperclass());
                }
        );
    }
}
