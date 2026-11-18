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

import com.asialjim.standard4j.code.Code;
import com.asialjim.standard4j.code.Codes;

import java.util.Optional;

/**
 * 政治面貌 —— 依据 <b>GB/T 4762《政治面貌代码》</b>（GB 4762-1984，两位数字码 01-13）：
 * 中国共产党党员、预备党员、共青团员、8 个民主党派、无党派民主人士与群众。
 *
 * <p>标准未提供"未知"码值，{@link #UNKNOWN} 按本库约定取 {@code "-"}。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum PoliticalAffiliation implements Code {

    /** 未知/未提供（无标准码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知"),

    /** 中国共产党党员（GB/T 4762: 01） */
    CPC_MEMBER("01", "中国共产党党员"),

    /** 中国共产党预备党员（GB/T 4762: 02） */
    CPC_PROBATIONARY_MEMBER("02", "中国共产党预备党员"),

    /** 中国共产主义青年团团员（GB/T 4762: 03；含解放初期中国新民主主义青年团团员） */
    CYL_MEMBER("03", "中国共产主义青年团团员"),

    /** 中国国民党革命委员会会员（GB/T 4762: 04） */
    RCCK_MEMBER("04", "中国国民党革命委员会会员"),

    /** 中国民主同盟盟员（GB/T 4762: 05） */
    CDL_MEMBER("05", "中国民主同盟盟员"),

    /** 中国民主建国会会员（GB/T 4762: 06） */
    CNDCA_MEMBER("06", "中国民主建国会会员"),

    /** 中国民主促进会会员（GB/T 4762: 07） */
    CAPD_MEMBER("07", "中国民主促进会会员"),

    /** 中国农工民主党党员（GB/T 4762: 08） */
    CPWDP_MEMBER("08", "中国农工民主党党员"),

    /** 中国致公党党员（GB/T 4762: 09） */
    CZGP_MEMBER("09", "中国致公党党员"),

    /** 九三学社社员（GB/T 4762: 10） */
    JIUSAN_MEMBER("10", "九三学社社员"),

    /** 台湾民主自治同盟盟员（GB/T 4762: 11） */
    TDSL_MEMBER("11", "台湾民主自治同盟盟员"),

    /** 无党派民主人士（GB/T 4762: 12） */
    NON_PARTISAN("12", "无党派民主人士"),

    /** 群众（GB/T 4762: 13） */
    MASS("13", "群众"),
    ;

    private final String code;
    private final String label;

    PoliticalAffiliation(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String label() {
        return this.label;
    }

    /**
     * UNKNOWN 项静态访问器
     *
     * @return {@link #UNKNOWN}
     */
    public static PoliticalAffiliation unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link PoliticalAffiliation}
     */
    public static PoliticalAffiliation of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link PoliticalAffiliation}&gt;
     */
    public static Optional<PoliticalAffiliation> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link PoliticalAffiliation}
     */
    public static PoliticalAffiliation ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
