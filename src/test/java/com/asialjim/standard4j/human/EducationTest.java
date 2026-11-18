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
 * {@link Education} 契约测试：GB/T 4658-2006 码值对齐。
 */
class EducationTest {

    @Test
    @DisplayName("GB/T 4658 码值对齐：末位 *1 毕业 / *2 结业 / *3 肄业")
    void nationalStandardCodes() {
        assertAll(
                () -> assertEquals("90", Education.UNKNOWN.code()),
                () -> assertEquals(Education.GRADUATE_EDU, Education.of("10")),
                () -> assertEquals(Education.DOCTOR_GRADUATED, Education.of("11")),
                () -> assertEquals(Education.MASTER_GRADUATED, Education.of("14")),
                () -> assertEquals(Education.UNDERGRAD_GRADUATED, Education.of("21")),
                () -> assertEquals(Education.ORDINARY_COLLEGE_CLASS, Education.of("28")),
                () -> assertEquals(Education.COLLEGE_GRADUATED, Education.of("31")),
                () -> assertEquals(Education.SECONDARY_SPECIAL_GRADUATED, Education.of("41")),
                () -> assertEquals(Education.VOCATIONAL_HIGH_GRADUATED, Education.of("44")),
                () -> assertEquals(Education.TECHNICAL_SCHOOL_GRADUATED, Education.of("47")),
                () -> assertEquals(Education.SENIOR_HIGH_GRADUATED, Education.of("61")),
                () -> assertEquals(Education.JUNIOR_HIGH_GRADUATED, Education.of("71")),
                () -> assertEquals(Education.PRIMARY_GRADUATED, Education.of("81"))
        );
    }

    @Test
    @DisplayName("标准无'未说明'码值：其他(90)兼任 UNKNOWN")
    void unknownConvention() {
        assertAll(
                () -> assertEquals(Education.UNKNOWN, Education.unknown()),
                () -> assertEquals(Education.UNKNOWN, Education.ofOrDefault("99"))
        );
    }

    @Test
    @DisplayName("学业状态后缀一致性：*1 毕业、*2 结业、*3 肄业")
    void statusSuffixConvention() {
        assertAll(
                () -> assertEquals(Education.UNDERGRAD_GRADUATED, Education.of("21")),
                () -> assertEquals(Education.UNDERGRAD_COMPLETED, Education.of("22")),
                () -> assertEquals(Education.UNDERGRAD_UNFINISHED, Education.of("23")),
                () -> assertEquals(Education.COLLEGE_GRADUATED, Education.of("31")),
                () -> assertEquals(Education.COLLEGE_COMPLETED, Education.of("32")),
                () -> assertEquals(Education.COLLEGE_UNFINISHED, Education.of("33"))
        );
    }
}
