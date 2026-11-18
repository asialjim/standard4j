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
 * 民族 —— 依据 <b>GB/T 3304《中国各民族名称的罗马字母拼写法和代码》</b>：2 位数字码（01 汉族 … 56 基诺族）。
 *
 * <p>注：旧库 {@code Nationality} 实为本标准（民族），命名有误导性；国籍/国家标准由 region 包的
 * 国家/地区字典承担，两者正交。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum EthnicGroup implements Code {

    /** 未知/未提供（无国标码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知"),

    /** 汉族（GB/T 3304: 01） */
    HAN("01", "汉族"),

    /** 蒙古族（GB/T 3304: 02） */
    MONGOL("02", "蒙古族"),

    /** 回族（GB/T 3304: 03） */
    HUI("03", "回族"),

    /** 藏族（GB/T 3304: 04） */
    TIBETAN("04", "藏族"),

    /** 维吾尔族（GB/T 3304: 05） */
    UYGHUR("05", "维吾尔族"),

    /** 苗族（GB/T 3304: 06） */
    MIAO("06", "苗族"),

    /** 彝族（GB/T 3304: 07） */
    YI("07", "彝族"),

    /** 壮族（GB/T 3304: 08） */
    ZHUANG("08", "壮族"),

    /** 布依族（GB/T 3304: 09） */
    BUYEI("09", "布依族"),

    /** 朝鲜族（GB/T 3304: 10） */
    KOREAN("10", "朝鲜族"),

    /** 满族（GB/T 3304: 11） */
    MANCHU("11", "满族"),

    /** 侗族（GB/T 3304: 12） */
    DONG("12", "侗族"),

    /** 瑶族（GB/T 3304: 13） */
    YAO("13", "瑶族"),

    /** 白族（GB/T 3304: 14） */
    BAI("14", "白族"),

    /** 土家族（GB/T 3304: 15） */
    TUJIA("15", "土家族"),

    /** 哈尼族（GB/T 3304: 16） */
    HANI("16", "哈尼族"),

    /** 哈萨克族（GB/T 3304: 17） */
    KAZAKH("17", "哈萨克族"),

    /** 傣族（GB/T 3304: 18） */
    DAI("18", "傣族"),

    /** 黎族（GB/T 3304: 19） */
    LI("19", "黎族"),

    /** 傈僳族（GB/T 3304: 20） */
    LISU("20", "傈僳族"),

    /** 佤族（GB/T 3304: 21） */
    VA("21", "佤族"),

    /** 畲族（GB/T 3304: 22） */
    SHE("22", "畲族"),

    /** 高山族（GB/T 3304: 23） */
    GAOSHAN("23", "高山族"),

    /** 拉祜族（GB/T 3304: 24） */
    LAHU("24", "拉祜族"),

    /** 水族（GB/T 3304: 25） */
    SUI("25", "水族"),

    /** 东乡族（GB/T 3304: 26） */
    DONGXIANG("26", "东乡族"),

    /** 纳西族（GB/T 3304: 27） */
    NAXI("27", "纳西族"),

    /** 景颇族（GB/T 3304: 28） */
    JINGPO("28", "景颇族"),

    /** 柯尔克孜族（GB/T 3304: 29） */
    KIRGIZ("29", "柯尔克孜族"),

    /** 土族（GB/T 3304: 30） */
    TU("30", "土族"),

    /** 达斡尔族（GB/T 3304: 31） */
    DAUR("31", "达斡尔族"),

    /** 仫佬族（GB/T 3304: 32） */
    MULAO("32", "仫佬族"),

    /** 羌族（GB/T 3304: 33） */
    QIANG("33", "羌族"),

    /** 布朗族（GB/T 3304: 34） */
    BLANG("34", "布朗族"),

    /** 撒拉族（GB/T 3304: 35） */
    SALAR("35", "撒拉族"),

    /** 毛南族（GB/T 3304: 36） */
    MAONAN("36", "毛南族"),

    /** 仡佬族（GB/T 3304: 37） */
    GELAO("37", "仡佬族"),

    /** 锡伯族（GB/T 3304: 38） */
    XIBE("38", "锡伯族"),

    /** 阿昌族（GB/T 3304: 39） */
    ACHANG("39", "阿昌族"),

    /** 普米族（GB/T 3304: 40） */
    PUMI("40", "普米族"),

    /** 塔吉克族（GB/T 3304: 41） */
    TAJIK("41", "塔吉克族"),

    /** 怒族（GB/T 3304: 42） */
    NU("42", "怒族"),

    /** 乌孜别克族（GB/T 3304: 43） */
    UZBEK("43", "乌孜别克族"),

    /** 俄罗斯族（GB/T 3304: 44） */
    RUSSIAN("44", "俄罗斯族"),

    /** 鄂温克族（GB/T 3304: 45） */
    EVENK("45", "鄂温克族"),

    /** 德昂族（GB/T 3304: 46） */
    DEANG("46", "德昂族"),

    /** 保安族（GB/T 3304: 47） */
    BONAN("47", "保安族"),

    /** 裕固族（GB/T 3304: 48） */
    YUGUR("48", "裕固族"),

    /** 京族（GB/T 3304: 49） */
    GIN("49", "京族"),

    /** 塔塔尔族（GB/T 3304: 50） */
    TATAR("50", "塔塔尔族"),

    /** 独龙族（GB/T 3304: 51） */
    DRUNG("51", "独龙族"),

    /** 鄂伦春族（GB/T 3304: 52） */
    OROQEN("52", "鄂伦春族"),

    /** 赫哲族（GB/T 3304: 53） */
    HEZHEN("53", "赫哲族"),

    /** 门巴族（GB/T 3304: 54） */
    MONBA("54", "门巴族"),

    /** 珞巴族（GB/T 3304: 55） */
    LHOBA("55", "珞巴族"),

    /** 基诺族（GB/T 3304: 56） */
    JINO("56", "基诺族"),
    ;

    private final String code;
    private final String label;

    EthnicGroup(String code, String label) {
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
    public static EthnicGroup unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link EthnicGroup}
     */
    public static EthnicGroup of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link EthnicGroup}&gt;
     */
    public static Optional<EthnicGroup> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link EthnicGroup}
     */
    public static EthnicGroup ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
