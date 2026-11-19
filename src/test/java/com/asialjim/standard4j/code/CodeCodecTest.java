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
import com.asialjim.standard4j.human.Gender;
import com.asialjim.standard4j.human.IdCardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 字典编解码与别名表 SPI 测试。
 */
class CodeCodecTest {

    @Test
    @DisplayName("CodeCodec：encode 取 code()，decode 委托 Codes 宽容查找")
    void defaultCodec() {
        assertAll(
                () -> assertEquals("1", CodeCodecs.DEFAULT.encode(Gender.MALE)),
                () -> assertNull(CodeCodecs.DEFAULT.encode(null)),
                () -> assertEquals(Optional.of(Gender.FEMALE), CodeCodecs.DEFAULT.decode("2", Gender.values())),
                () -> assertEquals(Optional.of(Gender.MALE), CodeCodecs.DEFAULT.decode("male", Gender.values())),
                () -> assertEquals(Optional.empty(), CodeCodecs.DEFAULT.decode("9x", Gender.values()))
        );
    }

    @Test
    @DisplayName("AliasTable：主码优先，未命中再查别名表")
    void aliasTable() {
        AliasTable<IdCardType> jrt = new AliasTable<>() {
            @Override
            public String namespace() {
                return "JR/T-0160";
            }

            @Override
            public Optional<IdCardType> ofNullable(String alias) {
                if (alias == null || alias.isBlank())
                    return Optional.empty();
                return switch (alias.trim()) {
                    case "1" -> Optional.of(IdCardType.RESIDENT_ID_CARD);
                    case "5" -> Optional.of(IdCardType.HOUSEHOLD_REGISTER);
                    case "6" -> Optional.of(IdCardType.PASSPORT);
                    case "11" -> Optional.of(IdCardType.DRIVER_LICENSE);
                    default -> Optional.empty();
                };
            }
        };
        assertAll(
                () -> assertEquals(Optional.of(IdCardType.RESIDENT_ID_CARD),
                        AliasTables.ofNullable(IdCardType.values(), "01", jrt)),
                () -> assertEquals(Optional.of(IdCardType.RESIDENT_ID_CARD),
                        AliasTables.ofNullable(IdCardType.values(), "1", jrt)),
                () -> assertEquals(Optional.of(IdCardType.PASSPORT),
                        AliasTables.ofNullable(IdCardType.values(), "6", jrt)),
                () -> assertEquals(Optional.empty(),
                        AliasTables.ofNullable(IdCardType.values(), "99x", jrt)),
                () -> assertEquals(IdCardType.HOUSEHOLD_REGISTER, jrt.of("5")),
                () -> assertThrows(BusinessException.class, () -> jrt.of("99x")),
                () -> assertEquals(IdCardType.UNKNOWN, jrt.ofOrDefault("99x", IdCardType.UNKNOWN))
        );
    }

    @Test
    @DisplayName("IdCardType 金融别名反查：严格 / Optional / UNKNOWN")
    void financialCodeReverseLookup() {
        assertAll(
                () -> assertEquals(IdCardType.RESIDENT_ID_CARD, IdCardType.ofFinancialCode("1")),
                () -> assertEquals(IdCardType.DRIVER_LICENSE, IdCardType.ofFinancialCode(" 11 ")),
                () -> assertEquals(Optional.of(IdCardType.PASSPORT), IdCardType.ofFinancialCodeNullable("6")),
                () -> assertEquals(Optional.empty(), IdCardType.ofFinancialCodeNullable("99x")),
                () -> assertEquals(IdCardType.UNKNOWN, IdCardType.ofFinancialCodeOrDefault("99x")),
                () -> assertThrows(BusinessException.class, () -> IdCardType.ofFinancialCode("99x"))
        );
    }
}
