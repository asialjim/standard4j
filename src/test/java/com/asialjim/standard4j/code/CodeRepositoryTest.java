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

package com.asialjim.standard4j.code;

import com.asialjim.standard4j.exception.BusinessException;
import com.asialjim.standard4j.industry.Industry;
import com.asialjim.standard4j.industry.IndustryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link CodeRepository}/{@link HierarchicalCodeRepository} 契约测试：
 * 以样例行业枚举 + 内存实现验证仓库三口径与层级查询。
 */
class CodeRepositoryTest {

    /** 测试样例：GB/T 4754 门类+大类的最小实现（实现 Industry 契约接口）。 */
    enum SampleIndustry implements Industry {
        UNKNOWN("-", "未知", 0, null),
        MANUFACTURING("C", "制造业", 0, null),
        IT_SERVICES("I65", "软件和信息技术服务业", 1, "C"),
        ;

        private final String code;
        private final String label;
        private final int level;
        private final String parentCode;

        SampleIndustry(String code, String label, int level, String parentCode) {
            this.code = code;
            this.label = label;
            this.level = level;
            this.parentCode = parentCode;
        }

        @Override
        public String code() {
            return this.code;
        }

        @Override
        public String label() {
            return this.label;
        }

        @Override
        public int level() {
            return this.level;
        }

        @Override
        public String parentCode() {
            return this.parentCode;
        }
    }

    /** 测试样例：枚举字典的仓库实现（内部委托 Codes，与枚举查询同口径）。 */
    static class SampleIndustryRepository implements IndustryRepository {
        @Override
        public Industry unknown() {
            return SampleIndustry.UNKNOWN;
        }

        @Override
        public Optional<Industry> ofNullable(String code) {
            return Codes.ofNullable(SampleIndustry.values(), code);
        }

        @Override
        public List<Industry> childrenOf(String parentCode) {
            return java.util.Arrays.stream(SampleIndustry.values())
                    .filter(i -> parentCode.equals(i.parentCode()))
                    .map(Industry.class::cast)
                    .toList();
        }
    }

    private final IndustryRepository repository = new SampleIndustryRepository();

    @Test
    @DisplayName("仓库三口径：严格抛错 / Optional / 落 UNKNOWN")
    void lookupTrio() {
        assertAll(
                () -> assertEquals(Industry.class.cast(SampleIndustry.IT_SERVICES), repository.of("I65")),
                () -> assertEquals(Optional.of(Industry.class.cast(SampleIndustry.MANUFACTURING)), repository.ofNullable("c")),
                () -> assertThrows(BusinessException.class, () -> repository.of("Z99")),
                () -> assertEquals(SampleIndustry.UNKNOWN, repository.ofOrDefault("Z99")),
                () -> assertEquals(SampleIndustry.UNKNOWN, repository.ofOrDefault(null))
        );
    }

    @Test
    @DisplayName("层级查询：parentOf / childrenOf")
    void hierarchicalQueries() {
        assertAll(
                () -> assertEquals(Optional.of(Industry.class.cast(SampleIndustry.MANUFACTURING)),
                        repository.parentOf("I65")),
                () -> assertEquals(Optional.empty(), repository.parentOf("C")),
                () -> assertEquals(List.of(SampleIndustry.IT_SERVICES), repository.childrenOf("C")),
                () -> assertEquals(List.of(), repository.childrenOf("I65"))
        );
    }
}
