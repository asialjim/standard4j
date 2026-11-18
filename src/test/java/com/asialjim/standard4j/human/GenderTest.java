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

import com.asialjim.standard4j.code.CodeView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link Gender} 查找三口径与视图契约测试 —— 同时作为全库字典枚举的行为基准。
 */
class GenderTest {

    @Test
    @DisplayName("严格查找：按 code 命中，不区分大小写")
    void ofByCode() {
        assertAll(
                () -> assertEquals(Gender.MALE, Gender.of("1")),
                () -> assertEquals(Gender.FEMALE, Gender.of("2")),
                () -> assertEquals(Gender.UNSTATED, Gender.of("9"))
        );
    }

    @Test
    @DisplayName("严格查找：按枚举常量名命中，不区分大小写")
    void ofByConstantName() {
        assertEquals(Gender.MALE, Gender.of("male"));
        assertEquals(Gender.FEMALE, Gender.of(" Female "));
    }

    @Test
    @DisplayName("严格查找：未匹配抛 IllegalArgumentException")
    void ofThrowsOnUnknown() {
        assertThrows(IllegalArgumentException.class, () -> Gender.of("5"));
        assertThrows(IllegalArgumentException.class, () -> Gender.of(null));
    }

    @Test
    @DisplayName("宽容解析：未匹配/无效入参一律落 UNKNOWN")
    void ofOrDefaultFallsBackToUnknown() {
        assertAll(
                () -> assertEquals(Gender.UNKNOWN, Gender.ofOrDefault("5")),
                () -> assertEquals(Gender.UNKNOWN, Gender.ofOrDefault(null)),
                () -> assertEquals(Gender.UNKNOWN, Gender.ofOrDefault("  ")),
                // "male" 是枚举常量名，宽容解析应命中而不是落 UNKNOWN
                () -> assertEquals(Gender.MALE, Gender.ofOrDefault("male"))
        );
    }

    @Test
    @DisplayName("ofNullable：命中与未命中")
    void ofNullable() {
        assertAll(
                () -> assertEquals(Optional.of(Gender.MALE), Gender.ofNullable("1")),
                () -> assertEquals(Optional.empty(), Gender.ofNullable("5"))
        );
    }

    @Test
    @DisplayName("CodeView：code + label 结构化视图")
    void view() {
        CodeView view = Gender.FEMALE.view();
        assertAll(
                () -> assertEquals("2", view.code()),
                () -> assertEquals("女", view.label())
        );
    }

    @Test
    @DisplayName("GB/T 2261.1 码值对齐")
    void nationalStandardCodes() {
        assertAll(
                () -> assertEquals("0", Gender.UNKNOWN.code()),
                () -> assertEquals("1", Gender.MALE.code()),
                () -> assertEquals("2", Gender.FEMALE.code()),
                () -> assertEquals("9", Gender.UNSTATED.code())
        );
    }
}
