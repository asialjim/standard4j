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
 * {@link Degree} 契约测试：GB/T 6864-2003 码值对齐。
 */
class DegreeTest {

    @Test
    @DisplayName("GB/T 6864 第一层：1 名誉博士 / 2 博士 / 3 硕士 / 4 学士")
    void topLevelCodes() {
        assertAll(
                () -> assertEquals(Degree.HONORARY_DOCTORATE, Degree.of("1")),
                () -> assertEquals(Degree.DOCTORATE, Degree.of("2")),
                () -> assertEquals(Degree.MASTER, Degree.of("3")),
                () -> assertEquals(Degree.BACHELOR, Degree.of("4"))
        );
    }

    @Test
    @DisplayName("学科门类对齐：第二层 01-12（哲学01 … 管理学12）")
    void disciplineCodes() {
        assertAll(
                () -> assertEquals(Degree.PHILOSOPHY_DOCTOR, Degree.of("201")),
                () -> assertEquals(Degree.PHILOSOPHY_MASTER, Degree.of("301")),
                () -> assertEquals(Degree.PHILOSOPHY_BACHELOR, Degree.of("401")),
                () -> assertEquals(Degree.MANAGEMENT_DOCTOR, Degree.of("212")),
                () -> assertEquals(Degree.MANAGEMENT_MASTER, Degree.of("312")),
                () -> assertEquals(Degree.MANAGEMENT_BACHELOR, Degree.of("412"))
        );
    }

    @Test
    @DisplayName("2003 文本专业学位：博士 245/248/250，硕士 341-352，学士 444")
    void professionalDegreeCodes() {
        assertAll(
                () -> assertEquals(Degree.CLINICAL_MEDICINE_DOCTOR_PRO, Degree.of("245")),
                () -> assertEquals(Degree.VETERINARY_DOCTOR_PRO, Degree.of("248")),
                () -> assertEquals(Degree.STOMATOLOGY_DOCTOR_PRO, Degree.of("250")),
                () -> assertEquals(Degree.LAW_MASTER_PRO, Degree.of("341")),
                () -> assertEquals(Degree.BUSINESS_ADMINISTRATION_MASTER_PRO, Degree.of("346")),
                () -> assertEquals(Degree.MILITARY_MASTER_PRO, Degree.of("352")),
                () -> assertEquals(Degree.ARCHITECTURE_BACHELOR_PRO, Degree.of("444")),
                () -> assertEquals(16, java.util.Arrays.stream(Degree.values())
                        .filter(d -> d.code().matches("[234]\\d{2}") && d.label().contains("专业学位"))
                        .count())
        );
    }

    @Test
    @DisplayName("标准无'未知'码值：按约定取 \"-\"")
    void unknownConvention() {
        assertAll(
                () -> assertEquals("-", Degree.UNKNOWN.code()),
                () -> assertEquals(Degree.UNKNOWN, Degree.unknown()),
                () -> assertEquals(Degree.UNKNOWN, Degree.ofOrDefault("999"))
        );
    }
}
