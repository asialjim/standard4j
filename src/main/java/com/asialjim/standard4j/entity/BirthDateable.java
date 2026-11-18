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

package com.asialjim.standard4j.entity;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.OptionalInt;

/**
 * 出生日期字段契约。
 *
 * <p><b>年龄一律为推导字段，不落存储</b>：只存 {@code birthDate}，年龄按
 * <b>周岁</b>（公历、满年满月满日）实时推导，口径见 {@link #ageOn(LocalDate)}。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface BirthDateable {

    /**
     * 出生日期（公历）
     *
     * @return 出生日期
     */
    LocalDate getBirthDate();

    /**
     * 设置出生日期
     *
     * @param birthDate 出生日期
     */
    void setBirthDate(LocalDate birthDate);

    /**
     * 按周岁计算指定日期当天的年龄。
     *
     * <p>规则：整年数相减，若当日尚未到达当年的生日（月/日早于出生月/日）则再减 1；
     * 即"未过生日不算长一岁"。2 月 29 日出生者，在平年按 2 月 28 日计（与户籍登记口径一致）。</p>
     *
     * @param on 计算基准日
     * @return 周岁；{@code birthDate} 或 {@code on} 为空时返回 {@link OptionalInt#empty()}
     */
    default OptionalInt ageOn(LocalDate on) {
        LocalDate birthDate = getBirthDate();
        if (birthDate == null || on == null)
            return OptionalInt.empty();
        int age = on.getYear() - birthDate.getYear();
        MonthDay birthday = MonthDay.of(birthDate.getMonthValue(), birthDate.getDayOfMonth());
        if (on.isBefore(birthday.atYear(on.getYear())))
            age--;
        return OptionalInt.of(Math.max(age, 0));
    }

    /**
     * 按今天计算周岁
     *
     * @return 周岁；{@code birthDate} 为空时返回 {@link OptionalInt#empty()}
     */
    default OptionalInt age() {
        return ageOn(LocalDate.now());
    }
}
