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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 金额模型 —— 不可变：{@link BigDecimal} 金额 + {@link Currency} 币种。
 *
 * <p><b>硬性规定：禁止用 double/float 表示金额</b>——金额一律用
 * {@link BigDecimal}（或经 {@link #ofMinor(long, Currency)} 传最小单位整数）。</p>
 *
 * <p>构造时按币种精度（{@link Currency#getMinorUnit()}）规约小数位：
 * 精度 {@code 2} 的币种要求金额恰好 2 位小数，多出的精度视为错误
 * （{@code RoundingMode.UNNECESSARY} 直接抛 {@code ArithmeticException}），
 * 避免静默舍入造成资损。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public record Money(BigDecimal amount, Currency currency) implements Comparable<Money> {

    /**
     * 紧凑构造：判空 + 按币种精度规约小数位
     */
    public Money {
        Objects.requireNonNull(amount, "金额不能为空");
        Objects.requireNonNull(currency, "币种不能为空");
        if (currency.getMinorUnit() >= 0)
            amount = amount.setScale(currency.getMinorUnit(), RoundingMode.UNNECESSARY);
    }

    /**
     * 以主单位金额构建（如 10.50 元人民币）
     *
     * @param amount   主单位金额，精度须与币种一致
     * @param currency 币种
     * @return {@link Money}
     */
    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    /**
     * 以最小单位整数构建（如 1050 分 → 10.50 元人民币）
     *
     * @param minor    最小单位金额
     * @param currency 币种
     * @return {@link Money}
     */
    public static Money ofMinor(long minor, Currency currency) {
        int scale = Math.max(currency.getMinorUnit(), 0);
        return new Money(BigDecimal.valueOf(minor, scale), currency);
    }

    /**
     * 最小单位金额（如 10.50 元人民币 → 1050 分）
     *
     * @return 最小单位整数金额
     */
    public long toMinor() {
        int scale = Math.max(this.currency.getMinorUnit(), 0);
        return this.amount.setScale(scale).movePointRight(scale).longValueExact();
    }

    /**
     * 同币种加法
     *
     * @param other 另一笔金额
     * @return {@link Money}
     */
    public Money add(Money other) {
        requireSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * 同币种减法
     *
     * @param other 另一笔金额
     * @return {@link Money}
     */
    public Money subtract(Money other) {
        requireSameCurrency(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    @Override
    public int compareTo(Money other) {
        requireSameCurrency(other);
        return this.amount.compareTo(other.amount);
    }

    private void requireSameCurrency(Money other) {
        if (this.currency != other.currency)
            throw new BusinessException(SystemResCode.CURRENCY_MISMATCH,
                    "币种不一致: " + this.currency.code() + " vs " + other.currency.code());
    }
}
