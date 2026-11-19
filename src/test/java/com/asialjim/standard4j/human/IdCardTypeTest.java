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

import com.asialjim.standard4j.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link IdCardType} 契约测试：GA/T 主码、JR/T 别名、脱敏元数据与查找三口径。
 */
class IdCardTypeTest {

    @Test
    @DisplayName("严格查找：GA/T 主码命中")
    void ofByGaCode() {
        assertAll(
                () -> assertEquals(IdCardType.RESIDENT_ID_CARD, IdCardType.of("01")),
                () -> assertEquals(IdCardType.HOUSEHOLD_REGISTER, IdCardType.of("02")),
                () -> assertEquals(IdCardType.DRIVER_LICENSE, IdCardType.of("03")),
                () -> assertEquals(IdCardType.PASSPORT, IdCardType.of("99"))
        );
    }

    @Test
    @DisplayName("严格查找：未匹配抛 BusinessException")
    void ofThrows() {
        assertThrows(BusinessException.class, () -> IdCardType.of("14"));
    }

    @Test
    @DisplayName("宽容解析：未匹配落 UNKNOWN")
    void ofOrDefault() {
        assertEquals(IdCardType.UNKNOWN, IdCardType.ofOrDefault("14"));
    }

    @Test
    @DisplayName("JR/T 0160-2018 金融别名码")
    void jrCodeAliases() {
        assertAll(
                () -> assertEquals("1", IdCardType.RESIDENT_ID_CARD.getFinancialCode()),
                () -> assertEquals("5", IdCardType.HOUSEHOLD_REGISTER.getFinancialCode()),
                () -> assertEquals("11", IdCardType.DRIVER_LICENSE.getFinancialCode()),
                () -> assertEquals("2", IdCardType.MILITARY_ID.getFinancialCode()),
                () -> assertEquals("6", IdCardType.PASSPORT.getFinancialCode()),
                () -> assertNull(IdCardType.SOCIAL_SECURITY_CARD.getFinancialCode()),
                () -> assertNull(IdCardType.BORDER_PASS.getFinancialCode())
        );
    }

    @Test
    @DisplayName("脱敏元数据：身份证 6/4、军官证 1/1、通行证 3/3")
    void sensitiveMetadata() {
        assertAll(
                () -> assertEquals(6, IdCardType.RESIDENT_ID_CARD.getSensitiveLeft()),
                () -> assertEquals(4, IdCardType.RESIDENT_ID_CARD.getSensitiveRight()),
                () -> assertEquals(1, IdCardType.MILITARY_ID.getSensitiveLeft()),
                () -> assertEquals(1, IdCardType.MILITARY_ID.getSensitiveRight()),
                () -> assertEquals(3, IdCardType.HK_MO_PASS.getSensitiveLeft()),
                () -> assertEquals(3, IdCardType.HK_MO_PASS.getSensitiveRight()),
                () -> assertEquals(6, IdCardType.RESIDENT_ID_CARD.sensitiveLeft()),
                () -> assertEquals(4, IdCardType.RESIDENT_ID_CARD.sensitiveRight()),
                () -> assertEquals("440304********0012",
                        IdCardType.RESIDENT_ID_CARD.maskedValue("440304200006150012"))
        );
    }
}
