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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link Money} 契约测试。
 */
class MoneyTest {

    @Test
    @DisplayName("最小单位换算：1050 分 ↔ 10.50 元")
    void minorUnitRoundtrip() {
        assertAll(
                () -> {
                    Money money = Money.ofMinor(1050, Currency.CNY);
                    assertEquals(new BigDecimal("10.50"), money.amount());
                    assertEquals(1050L, money.toMinor());
                },
                () -> {
                    // 日元无小数位
                    Money money = Money.ofMinor(1050, Currency.JPY);
                    assertEquals(new BigDecimal("1050"), money.amount());
                    assertEquals(1050L, money.toMinor());
                }
        );
    }

    @Test
    @DisplayName("同币种加法/减法/比较")
    void arithmetic() {
        Money a = Money.ofMinor(1050, Currency.CNY);
        Money b = Money.ofMinor(950, Currency.CNY);
        assertAll(
                () -> assertEquals(Money.ofMinor(2000, Currency.CNY), a.add(b)),
                () -> assertEquals(Money.ofMinor(100, Currency.CNY), a.subtract(b)),
                () -> assertEquals(1, a.compareTo(b))
        );
    }

    @Test
    @DisplayName("币种不一致抛 BusinessException（STD-00002）")
    void currencyMismatch() {
        assertAll(
                () -> assertThrows(BusinessException.class,
                        () -> Money.ofMinor(100, Currency.CNY).add(Money.ofMinor(100, Currency.USD))),
                () -> assertThrows(BusinessException.class,
                        () -> Money.ofMinor(100, Currency.CNY).compareTo(Money.ofMinor(100, Currency.USD)))
        );
    }

    @Test
    @DisplayName("精度规约：精度不足可自动补齐，超出精度直接报错（不静默舍入）")
    void scaleEnforcement() {
        assertAll(
                () -> assertEquals(new BigDecimal("10.50"), Money.of(new BigDecimal("10.5"), Currency.CNY).amount()),
                () -> assertThrows(ArithmeticException.class,
                        () -> Money.of(new BigDecimal("10.505"), Currency.CNY))
        );
    }
}
