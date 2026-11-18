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

/**
 * {@link EthnicGroup} 契约测试：GB/T 3304 码值对齐与查找三口径。
 */
class EthnicGroupTest {

    @Test
    @DisplayName("GB/T 3304 码值对齐：01 汉族 … 56 基诺族，共 56 个民族")
    void nationalStandardCodes() {
        assertAll(
                () -> assertEquals(56, java.util.Arrays.stream(EthnicGroup.values())
                        .filter(g -> g.code().matches("\\d{2}")).count()),
                () -> assertEquals(EthnicGroup.HAN, EthnicGroup.of("01")),
                () -> assertEquals(EthnicGroup.MONGOL, EthnicGroup.of("02")),
                () -> assertEquals(EthnicGroup.HUI, EthnicGroup.of("03")),
                () -> assertEquals(EthnicGroup.UYGHUR, EthnicGroup.of("05")),
                () -> assertEquals(EthnicGroup.ZHUANG, EthnicGroup.of("08")),
                () -> assertEquals(EthnicGroup.KOREAN, EthnicGroup.of("10")),
                () -> assertEquals(EthnicGroup.MANCHU, EthnicGroup.of("11")),
                () -> assertEquals(EthnicGroup.JINO, EthnicGroup.of("56"))
        );
    }

    @Test
    @DisplayName("严格查找支持枚举常量名，不区分大小写")
    void ofByConstantName() {
        assertEquals(EthnicGroup.TIBETAN, EthnicGroup.of("tibetan"));
    }

    @Test
    @DisplayName("宽容解析：未匹配落 UNKNOWN")
    void ofOrDefault() {
        assertEquals(EthnicGroup.UNKNOWN, EthnicGroup.ofOrDefault("99"));
        assertEquals(EthnicGroup.UNKNOWN, EthnicGroup.ofOrDefault(null));
    }

    @Test
    @DisplayName("UNKNOWN 访问器与码值约定（\"-\"）")
    void unknownConvention() {
        assertAll(
                () -> assertEquals(EthnicGroup.UNKNOWN, EthnicGroup.unknown()),
                () -> assertEquals("-", EthnicGroup.UNKNOWN.code())
        );
    }

    @Test
    @DisplayName("世界族裔：非国标扩展码 X- 前缀，legacyCode 保留旧三字码")
    void worldEthnicityEntries() {
        assertAll(
                () -> assertEquals(EthnicGroup.ARAB, EthnicGroup.of("X-ARA")),
                () -> assertEquals(EthnicGroup.JAPANESE, EthnicGroup.of("X-JPN")),
                () -> assertEquals(EthnicGroup.ARAB, EthnicGroup.ofLegacy("ARA")),
                () -> assertEquals(EthnicGroup.JEWISH, EthnicGroup.ofLegacy("JEW")),
                () -> assertEquals(63, java.util.Arrays.stream(EthnicGroup.values())
                        .filter(g -> g.code().startsWith("X-")).count()),
                // 1 UNKNOWN + 56 GB/T 3304 + 63 世界族裔
                () -> assertEquals(120, EthnicGroup.values().length)
        );
    }

    @Test
    @DisplayName("旧库三字码查找（非标准，仅供迁移），重复码已消歧")
    void legacyCodeLookup() {
        assertAll(
                () -> assertEquals(EthnicGroup.HAN, EthnicGroup.ofLegacy("han")),
                () -> assertEquals(EthnicGroup.EVENK, EthnicGroup.ofLegacy("EVE")),
                // 旧库 EVE/JIN 重复：鄂伦春消歧为 ELC、基诺消歧为 JNO
                () -> assertEquals(EthnicGroup.OROQEN, EthnicGroup.ofLegacy("ELC")),
                () -> assertEquals(EthnicGroup.GIN, EthnicGroup.ofLegacy("JIN")),
                () -> assertEquals(EthnicGroup.JINO, EthnicGroup.ofLegacy("JNO")),
                () -> assertEquals(EthnicGroup.UNKNOWN, EthnicGroup.ofLegacyOrDefault("XXX"))
        );
    }
}
