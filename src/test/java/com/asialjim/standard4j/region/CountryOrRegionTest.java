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

package com.asialjim.standard4j.region;

import com.asialjim.standard4j.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link CountryOrRegion} 契约测试：ISO 3166-1 全量 alpha-2 对齐。
 */
class CountryOrRegionTest {

    @Test
    @DisplayName("ISO 3166-1 全量：249 个正式分配的 alpha-2 码")
    void fullCoverage() {
        assertAll(
                () -> assertEquals(249, CountryOrRegion.values().length - 1),
                () -> assertEquals(CountryOrRegion.CHINA, CountryOrRegion.of("CN")),
                () -> assertEquals(CountryOrRegion.UNITED_STATES, CountryOrRegion.of("US")),
                () -> assertEquals(CountryOrRegion.UNITED_KINGDOM, CountryOrRegion.of("GB")),
                () -> assertEquals(CountryOrRegion.JAPAN, CountryOrRegion.of("JP"))
        );
    }

    @Test
    @DisplayName("严格查找支持枚举常量名，不区分大小写")
    void ofByConstantName() {
        assertEquals(CountryOrRegion.CHINA, CountryOrRegion.of("china"));
        assertEquals(CountryOrRegion.FRANCE, CountryOrRegion.of(" France "));
    }

    @Test
    @DisplayName("严格查找：未匹配抛 BusinessException，宽容解析落 UNKNOWN")
    void strictAndTolerant() {
        assertAll(
                () -> assertThrows(BusinessException.class, () -> CountryOrRegion.of("XX")),
                () -> assertEquals(CountryOrRegion.UNKNOWN, CountryOrRegion.ofOrDefault("XX")),
                () -> assertEquals(CountryOrRegion.UNKNOWN, CountryOrRegion.ofOrDefault(null))
        );
    }

    @Test
    @DisplayName("alpha-3 别名码查找三口径")
    void alpha3Lookup() {
        assertAll(
                () -> assertEquals(CountryOrRegion.CHINA, CountryOrRegion.ofAlpha3("CHN")),
                () -> assertEquals(CountryOrRegion.CHINA, CountryOrRegion.ofAlpha3("chn")),
                () -> assertEquals(CountryOrRegion.UNKNOWN, CountryOrRegion.ofAlpha3OrDefault("XXX")),
                () -> assertThrows(BusinessException.class, () -> CountryOrRegion.ofAlpha3("XXX"))
        );
    }

    @Test
    @DisplayName("电信区号（ITU-T E.164）：兼容 86 / +86 / 0086 三种入参形态")
    void iddLookup() {
        assertAll(
                () -> assertEquals(CountryOrRegion.CHINA, CountryOrRegion.ofIdd("86")),
                () -> assertEquals(CountryOrRegion.CHINA, CountryOrRegion.ofIdd("+86")),
                () -> assertEquals(CountryOrRegion.CHINA, CountryOrRegion.ofIdd("0086")),
                () -> assertEquals(CountryOrRegion.HONG_KONG, CountryOrRegion.ofIdd("852")),
                () -> assertEquals("86", CountryOrRegion.CHINA.getIdd()),
                () -> assertEquals("886", CountryOrRegion.TAIWAN.getIdd()),
                () -> assertEquals("", CountryOrRegion.ANTARCTICA.getIdd()),
                () -> assertEquals(CountryOrRegion.UNKNOWN, CountryOrRegion.ofIddOrDefault("999"))
        );
    }

    @Test
    @DisplayName("共享区号多国命中：+1（美国/加拿大）、+7（俄罗斯/哈萨克斯坦）")
    void sharedIddCodes() {
        assertAll(
                () -> assertEquals(java.util.List.of(CountryOrRegion.CANADA, CountryOrRegion.UNITED_STATES),
                        CountryOrRegion.ofIddAll("1")),
                () -> assertEquals(java.util.List.of(CountryOrRegion.KAZAKHSTAN, CountryOrRegion.RUSSIA),
                        CountryOrRegion.ofIddAll("7"))
        );
    }

    @Test
    @DisplayName("UNKNOWN 约定（\"-\"）与中文译名抽查")
    void unknownAndLabels() {
        assertAll(
                () -> assertEquals(CountryOrRegion.UNKNOWN, CountryOrRegion.unknown()),
                () -> assertEquals("-", CountryOrRegion.UNKNOWN.code()),
                () -> assertEquals("中国", CountryOrRegion.CHINA.label()),
                () -> assertEquals("刚果（金）", CountryOrRegion.DR_CONGO.label()),
                () -> assertEquals("土耳其", CountryOrRegion.TURKIYE.label())
        );
    }
}
