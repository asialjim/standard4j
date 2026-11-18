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

package com.asialjim.standard4j.money;

import com.asialjim.standard4j.exception.BusinessException;
import com.asialjim.standard4j.exception.SystemResCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link Currency} 契约测试：ISO 4217 现行全量码表。
 */
class CurrencyTest {

    @Test
    @DisplayName("ISO 4217 现行全量：179 个代码 + UNKNOWN")
    void fullCoverage() {
        assertAll(
                () -> assertEquals(179, Currency.values().length - 1),
                () -> assertEquals(Currency.CNY, Currency.of("CNY")),
                () -> assertEquals(Currency.USD, Currency.of("USD")),
                () -> assertEquals(Currency.CNY, Currency.of("cny"))
        );
    }

    @Test
    @DisplayName("数字码与精度元数据：CNY 156/2、JPY 392/0、XAU 959/-1")
    void metadata() {
        assertAll(
                () -> assertEquals("156", Currency.CNY.getNumericCode()),
                () -> assertEquals(2, Currency.CNY.getMinorUnit()),
                () -> assertEquals("392", Currency.JPY.getNumericCode()),
                () -> assertEquals(0, Currency.JPY.getMinorUnit()),
                () -> assertEquals(-1, Currency.XAU.getMinorUnit()),
                () -> assertEquals("924", Currency.ZWG.getNumericCode())
        );
    }

    @Test
    @DisplayName("数字码查找三口径")
    void numericCodeLookup() {
        assertAll(
                () -> assertEquals(Currency.CNY, Currency.ofNumericCode("156")),
                () -> assertEquals(Currency.USD, Currency.ofNumericCode("840")),
                // XXX(999) 是 ISO 4217 的"无货币"码，不是未分配码；未分配码用 000
                () -> assertEquals(Currency.XXX, Currency.ofNumericCode("999")),
                () -> assertEquals(Currency.UNKNOWN, Currency.ofNumericCodeOrDefault("000")),
                () -> assertThrows(BusinessException.class, () -> Currency.ofNumericCode("000"))
        );
    }

    @Test
    @DisplayName("严格查找抛 BusinessException，宽容解析落 UNKNOWN")
    void strictAndTolerant() {
        assertAll(
                () -> assertThrows(BusinessException.class, () -> Currency.of("ZZZ")),
                () -> assertEquals(Currency.UNKNOWN, Currency.ofOrDefault("ZZZ")),
                () -> assertEquals(Currency.UNKNOWN, Currency.ofOrDefault(null))
        );
    }
}
