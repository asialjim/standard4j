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

package com.asialjim.standard4j.i18n;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link Language} 契约测试：ISO 639-1 常用语言子集（使用人数 &gt; 5000 万）。
 */
class LanguageTest {

    @Test
    @DisplayName("码值对齐：zh/en/es/fr 等常用语种")
    void codes() {
        assertAll(
                () -> assertEquals(Language.CHINESE, Language.of("zh")),
                () -> assertEquals(Language.ENGLISH, Language.of("en")),
                () -> assertEquals(Language.SPANISH, Language.of("es")),
                () -> assertEquals(Language.ARABIC, Language.of("ar")),
                () -> assertEquals(Language.CHINESE, Language.of("ZH"))
        );
    }

    @Test
    @DisplayName("严格查找抛 BusinessException，宽容解析落 UNKNOWN")
    void strictAndTolerant() {
        assertAll(
                () -> assertThrows(Exception.class, () -> Language.of("xx")),
                () -> assertEquals(Language.UNKNOWN, Language.ofOrDefault("xx")),
                () -> assertEquals(Language.UNKNOWN, Language.ofOrDefault(null))
        );
    }

    @Test
    @DisplayName("UNKNOWN 约定（\"-\"）")
    void unknownConvention() {
        assertAll(
                () -> assertEquals("-", Language.UNKNOWN.code()),
                () -> assertEquals(Language.UNKNOWN, Language.unknown())
        );
    }
}
