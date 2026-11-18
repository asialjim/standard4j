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
 * {@link PoliticalAffiliation} 契约测试：GB/T 4762 码值对齐。
 */
class PoliticalAffiliationTest {

    @Test
    @DisplayName("GB/T 4762 码值对齐：01 中共党员 … 13 群众，共 13 项")
    void nationalStandardCodes() {
        assertAll(
                () -> assertEquals(13, java.util.Arrays.stream(PoliticalAffiliation.values())
                        .filter(p -> p.code().matches("\\d{2}")).count()),
                () -> assertEquals(PoliticalAffiliation.CPC_MEMBER, PoliticalAffiliation.of("01")),
                () -> assertEquals(PoliticalAffiliation.CPC_PROBATIONARY_MEMBER, PoliticalAffiliation.of("02")),
                () -> assertEquals(PoliticalAffiliation.CYL_MEMBER, PoliticalAffiliation.of("03")),
                () -> assertEquals(PoliticalAffiliation.RCCK_MEMBER, PoliticalAffiliation.of("04")),
                () -> assertEquals(PoliticalAffiliation.JIUSAN_MEMBER, PoliticalAffiliation.of("10")),
                () -> assertEquals(PoliticalAffiliation.TDSL_MEMBER, PoliticalAffiliation.of("11")),
                () -> assertEquals(PoliticalAffiliation.NON_PARTISAN, PoliticalAffiliation.of("12")),
                () -> assertEquals(PoliticalAffiliation.MASS, PoliticalAffiliation.of("13"))
        );
    }

    @Test
    @DisplayName("标准无'未知'码值：按约定取 \"-\"")
    void unknownConvention() {
        assertAll(
                () -> assertEquals("-", PoliticalAffiliation.UNKNOWN.code()),
                () -> assertEquals(PoliticalAffiliation.UNKNOWN, PoliticalAffiliation.unknown()),
                () -> assertEquals(PoliticalAffiliation.UNKNOWN, PoliticalAffiliation.ofOrDefault("99"))
        );
    }

    @Test
    @DisplayName("查找支持枚举常量名")
    void ofByConstantName() {
        assertEquals(PoliticalAffiliation.CPC_MEMBER, PoliticalAffiliation.of("cpc_member"));
    }
}
