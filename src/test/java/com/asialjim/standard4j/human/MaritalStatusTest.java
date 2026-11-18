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

package com.asialjim.standard4j.human;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * {@link MaritalStatus} 契约测试：GB/T 2261.2-2003 码值对齐。
 */
class MaritalStatusTest {

    @Test
    @DisplayName("GB/T 2261.2 码值对齐：10 未婚 … 90 未说明")
    void nationalStandardCodes() {
        assertAll(
                () -> assertEquals("90", MaritalStatus.UNKNOWN.code()),
                () -> assertEquals(MaritalStatus.UNMARRIED, MaritalStatus.of("10")),
                () -> assertEquals(MaritalStatus.MARRIED, MaritalStatus.of("20")),
                () -> assertEquals(MaritalStatus.FIRST_MARRIAGE, MaritalStatus.of("21")),
                () -> assertEquals(MaritalStatus.REMARRIAGE, MaritalStatus.of("22")),
                () -> assertEquals(MaritalStatus.RESTORED_MARRIAGE, MaritalStatus.of("23")),
                () -> assertEquals(MaritalStatus.WIDOWED, MaritalStatus.of("30")),
                () -> assertEquals(MaritalStatus.DIVORCED, MaritalStatus.of("40"))
        );
    }

    @Test
    @DisplayName("标准自带'未说明'(90)兼任 UNKNOWN")
    void unknownIsStandardCode() {
        assertAll(
                () -> assertEquals(MaritalStatus.UNKNOWN, MaritalStatus.unknown()),
                () -> assertEquals(MaritalStatus.UNKNOWN, MaritalStatus.ofOrDefault("99"))
        );
    }

    @Test
    @DisplayName("查找支持枚举常量名")
    void ofByConstantName() {
        assertEquals(MaritalStatus.DIVORCED, MaritalStatus.of("divorced"));
        assertNull(MaritalStatus.ofNullable("99").orElse(null));
    }
}
