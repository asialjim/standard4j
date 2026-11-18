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
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * 民族 —— 依据 <b>GB/T 3304《中国各民族名称的罗马字母拼写法和代码》</b>：2 位数字码（01 汉族 … 56 基诺族）。
 *
 * <p>附注：</p>
 * <ul>
 *     <li>旧库 {@code Nationality} 实为本标准（民族），命名有误导性；国籍/国家标准由 region 包的
 *         国家/地区字典承担，两者正交；</li>
 *     <li>{@link #getLegacyCode()} 为旧库自定义三字码（<b>非标准</b>，仅供历史数据迁移），标准场景一律使用
 *         GB/T 3304 数字码；旧库三字码存在重复（{@code EVE} 同时用于鄂温克族与鄂伦春族、
 *         {@code JIN} 同时用于京族与基诺族），本枚举已消歧：鄂伦春族 {@code ELC}、基诺族 {@code JNO}；</li>
 *     <li>除 GB/T 3304 的 56 个中国民族外，本枚举还收录旧库中的<b>世界主要族裔</b>（非国标扩展）：
 *         其 {@link #code()} 统一加 {@code X-} 前缀标识（如 {@code X-ARA} 阿拉伯人），与 GB 数字码明确区分；
 *         {@link #getLegacyCode()} 保留旧库三字码；</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum EthnicGroup implements Code {

    /** 未知/未提供（无国标码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知", "Unknown", "-"),

    /** 汉族（GB/T 3304: 01） */
    HAN("01", "汉族", "Han", "HAN"),

    /** 蒙古族（GB/T 3304: 02） */
    MONGOL("02", "蒙古族", "Mongolian", "MGL"),

    /** 回族（GB/T 3304: 03） */
    HUI("03", "回族", "Hui", "HUI"),

    /** 藏族（GB/T 3304: 04） */
    TIBETAN("04", "藏族", "Tibetan", "TIB"),

    /** 维吾尔族（GB/T 3304: 05） */
    UYGHUR("05", "维吾尔族", "Uyghur", "UIG"),

    /** 苗族（GB/T 3304: 06） */
    MIAO("06", "苗族", "Miao", "MIA"),

    /** 彝族（GB/T 3304: 07） */
    YI("07", "彝族", "Yi", "YI"),

    /** 壮族（GB/T 3304: 08） */
    ZHUANG("08", "壮族", "Zhuang", "ZHA"),

    /** 布依族（GB/T 3304: 09） */
    BUYEI("09", "布依族", "Buyei", "BUI"),

    /** 朝鲜族（GB/T 3304: 10） */
    KOREAN("10", "朝鲜族", "Korean", "KOR"),

    /** 满族（GB/T 3304: 11） */
    MANCHU("11", "满族", "Manchu", "MAN"),

    /** 侗族（GB/T 3304: 12） */
    DONG("12", "侗族", "Dong", "DON"),

    /** 瑶族（GB/T 3304: 13） */
    YAO("13", "瑶族", "Yao", "YAO"),

    /** 白族（GB/T 3304: 14） */
    BAI("14", "白族", "Bai", "BAI"),

    /** 土家族（GB/T 3304: 15） */
    TUJIA("15", "土家族", "Tujia", "TUJ"),

    /** 哈尼族（GB/T 3304: 16） */
    HANI("16", "哈尼族", "Hani", "HNI"),

    /** 哈萨克族（GB/T 3304: 17） */
    KAZAKH("17", "哈萨克族", "Kazakh", "KAZ"),

    /** 傣族（GB/T 3304: 18） */
    DAI("18", "傣族", "Dai", "DAI"),

    /** 黎族（GB/T 3304: 19） */
    LI("19", "黎族", "Li", "LI"),

    /** 傈僳族（GB/T 3304: 20） */
    LISU("20", "傈僳族", "Lisu", "LSU"),

    /** 佤族（GB/T 3304: 21） */
    VA("21", "佤族", "Va", "VA"),

    /** 畲族（GB/T 3304: 22） */
    SHE("22", "畲族", "She", "SHE"),

    /** 高山族（GB/T 3304: 23） */
    GAOSHAN("23", "高山族", "Gaoshan", "GSH"),

    /** 拉祜族（GB/T 3304: 24） */
    LAHU("24", "拉祜族", "Lahu", "LAH"),

    /** 水族（GB/T 3304: 25） */
    SUI("25", "水族", "Shui", "SHU"),

    /** 东乡族（GB/T 3304: 26） */
    DONGXIANG("26", "东乡族", "Dongxiang", "DXX"),

    /** 纳西族（GB/T 3304: 27） */
    NAXI("27", "纳西族", "Naxi", "NAX"),

    /** 景颇族（GB/T 3304: 28） */
    JINGPO("28", "景颇族", "Jingpo", "JPO"),

    /** 柯尔克孜族（GB/T 3304: 29） */
    KIRGIZ("29", "柯尔克孜族", "Kirgiz", "KIR"),

    /** 土族（GB/T 3304: 30） */
    TU("30", "土族", "Tu", "TU"),

    /** 达斡尔族（GB/T 3304: 31） */
    DAUR("31", "达斡尔族", "Daur", "DAU"),

    /** 仫佬族（GB/T 3304: 32） */
    MULAO("32", "仫佬族", "Mulao", "MUL"),

    /** 羌族（GB/T 3304: 33） */
    QIANG("33", "羌族", "Qiang", "QIA"),

    /** 布朗族（GB/T 3304: 34） */
    BLANG("34", "布朗族", "Blang", "PUB"),

    /** 撒拉族（GB/T 3304: 35） */
    SALAR("35", "撒拉族", "Salar", "SAR"),

    /** 毛南族（GB/T 3304: 36） */
    MAONAN("36", "毛南族", "Maonan", "MON"),

    /** 仡佬族（GB/T 3304: 37） */
    GELAO("37", "仡佬族", "Gelao", "GLA"),

    /** 锡伯族（GB/T 3304: 38） */
    XIBE("38", "锡伯族", "Xibe", "XIB"),

    /** 阿昌族（GB/T 3304: 39） */
    ACHANG("39", "阿昌族", "Achang", "ACH"),

    /** 普米族（GB/T 3304: 40） */
    PUMI("40", "普米族", "Pumi", "PMI"),

    /** 塔吉克族（GB/T 3304: 41） */
    TAJIK("41", "塔吉克族", "Tajik", "TAJ"),

    /** 怒族（GB/T 3304: 42） */
    NU("42", "怒族", "Nu", "NU"),

    /** 乌孜别克族（GB/T 3304: 43） */
    UZBEK("43", "乌孜别克族", "Uzbek", "UZB"),

    /** 俄罗斯族（GB/T 3304: 44） */
    RUSSIAN("44", "俄罗斯族", "Russian", "RUS"),

    /** 鄂温克族（GB/T 3304: 45；旧三字码 EVE） */
    EVENK("45", "鄂温克族", "Evenki", "EVE"),

    /** 德昂族（GB/T 3304: 46） */
    DEANG("46", "德昂族", "Deang", "DEA"),

    /** 保安族（GB/T 3304: 47） */
    BONAN("47", "保安族", "Bonan", "BON"),

    /** 裕固族（GB/T 3304: 48） */
    YUGUR("48", "裕固族", "Yugur", "YUG"),

    /** 京族（GB/T 3304: 49；旧三字码 JIN） */
    GIN("49", "京族", "Jing", "JIN"),

    /** 塔塔尔族（GB/T 3304: 50） */
    TATAR("50", "塔塔尔族", "Tatar", "TAT"),

    /** 独龙族（GB/T 3304: 51） */
    DRUNG("51", "独龙族", "Dulong", "DUL"),

    /** 鄂伦春族（GB/T 3304: 52；旧库三字码误用 EVE（与鄂温克族重复），消歧为 ELC） */
    OROQEN("52", "鄂伦春族", "Ewenki", "ELC"),

    /** 赫哲族（GB/T 3304: 53） */
    HEZHEN("53", "赫哲族", "Hezhen", "HEZ"),

    /** 门巴族（GB/T 3304: 54） */
    MONBA("54", "门巴族", "Menba", "MEN"),

    /** 珞巴族（GB/T 3304: 55） */
    LHOBA("55", "珞巴族", "Lhoba", "LHO"),

    /** 基诺族（GB/T 3304: 56；旧库三字码误用 JIN（与京族重复），消歧为 JNO） */
    JINO("56", "基诺族", "Jino", "JNO"),

    // ---------------- 世界族裔（非国标扩展：code 统一加 X- 前缀） ----------------

    /** 阿拉伯人（非国标扩展码 X-ARA） */
    ARAB("X-ARA", "阿拉伯人", "Arab", "ARA"),

    /** 印度人（非国标扩展码 X-IND） */
    INDIAN("X-IND", "印度人", "Indian", "IND"),

    /** 日本人（非国标扩展码 X-JPN） */
    JAPANESE("X-JPN", "日本人", "Japanese", "JPN"),

    /** 旁遮普人（非国标扩展码 X-PUN） */
    PUNJABI("X-PUN", "旁遮普人", "Punjabi", "PUN"),

    /** 孟加拉人（非国标扩展码 X-BEN） */
    BENGALI("X-BEN", "孟加拉人", "Bengali", "BEN"),

    /** 贾特人（非国标扩展码 X-JAT） */
    JAT("X-JAT", "贾特人", "Jat", "JAT"),

    /** 土耳其人（非国标扩展码 X-TUR） */
    TURK("X-TUR", "土耳其人", "Turk", "TUR"),

    /** 普什图人（非国标扩展码 X-PAS） */
    PASHTUN("X-PAS", "普什图人", "Pashtun", "PAS"),

    /** 德意志人（非国标扩展码 X-GER） */
    GERMAN("X-GER", "德意志人", "German", "GER"),

    /** 法兰西人（非国标扩展码 X-FRA） */
    FRENCH("X-FRA", "法兰西人", "French", "FRA"),

    /** 不列颠人（非国标扩展码 X-GBR） */
    BRITISH("X-GBR", "不列颠人", "British", "GBR"),

    /** 意大利人（非国标扩展码 X-ITA） */
    ITALIAN("X-ITA", "意大利人", "Italian", "ITA"),

    /** 西班牙人（非国标扩展码 X-ESP） */
    SPANISH("X-ESP", "西班牙人", "Spanish", "ESP"),

    /** 墨西哥人（非国标扩展码 X-MEX） */
    MEXICAN("X-MEX", "墨西哥人", "Mexican", "MEX"),

    /** 美利坚人（非国标扩展码 X-USA） */
    AMERICAN("X-USA", "美利坚人", "American", "USA"),

    /** 巴西人（非国标扩展码 X-BRA） */
    BRAZILIAN("X-BRA", "巴西人", "Brazilian", "BRA"),

    /** 豪萨-富拉尼人（非国标扩展码 X-NGA） */
    HAUSA_FULANI("X-NGA", "豪萨-富拉尼人", "Hausa-Fulani", "NGA"),

    /** 约鲁巴人（非国标扩展码 X-YOR） */
    YORUBA("X-YOR", "约鲁巴人", "Yoruba", "YOR"),

    /** 伊博人（非国标扩展码 X-IGB） */
    IGBO("X-IGB", "伊博人", "Igbo", "IGB"),

    /** 索马里人（非国标扩展码 X-SOM） */
    SOMALI("X-SOM", "索马里人", "Somali", "SOM"),

    /** 阿姆哈拉人（非国标扩展码 X-AMH） */
    AMHARA("X-AMH", "阿姆哈拉人", "Amhara", "AMH"),

    /** 奥罗莫人（非国标扩展码 X-ORO） */
    OROMO("X-ORO", "奥罗莫人", "Oromo", "ORO"),

    /** 土库曼人（非国标扩展码 X-TKM） */
    TURKMEN("X-TKM", "土库曼人", "Turkmen", "TKM"),

    /** 柯尔克孜人（非国标扩展码 X-KGZ；中国境内柯尔克孜族见 GB/T 3304: 29） */
    KYRGYZ("X-KGZ", "柯尔克孜人", "Kyrgyz", "KGZ"),

    /** 布里亚特人（非国标扩展码 X-BRT） */
    BURYAT("X-BRT", "布里亚特人", "Buryat", "BRT"),

    /** 毛利人（非国标扩展码 X-MAO） */
    MAORI("X-MAO", "毛利人", "Maori", "MAO"),

    /** 澳大利亚原住民（非国标扩展码 X-ABO） */
    ABORIGINAL("X-ABO", "澳大利亚原住民", "Aboriginal", "ABO"),

    /** 因纽特人（非国标扩展码 X-INU） */
    INUIT("X-INU", "因纽特人", "Inuit", "INU"),

    /** 萨米人（非国标扩展码 X-SAMI；旧三字码为四字符） */
    SAMI("X-SAMI", "萨米人", "Sami", "SAMI"),

    /** 柏柏尔人（非国标扩展码 X-BRB） */
    BERBER("X-BRB", "柏柏尔人", "Berber", "BRB"),

    /** 富拉尼人（非国标扩展码 X-FUL） */
    FULANI("X-FUL", "富拉尼人", "Fulani", "FUL"),

    /** 祖鲁人（非国标扩展码 X-ZUL） */
    ZULU("X-ZUL", "祖鲁人", "Zulu", "ZUL"),

    /** 科萨人（非国标扩展码 X-XHO） */
    XHOSA("X-XHO", "科萨人", "Xhosa", "XHO"),

    /** 索托人（非国标扩展码 X-SOT） */
    SOTHO("X-SOT", "索托人", "Sotho", "SOT"),

    /** 茨瓦纳人（非国标扩展码 X-TSW） */
    TSWANA("X-TSW", "茨瓦纳人", "Tswana", "TSW"),

    /** 斯威士人（非国标扩展码 X-SWA） */
    SWAZI("X-SWA", "斯威士人", "Swazi", "SWA"),

    /** 绍纳人（非国标扩展码 X-SHO） */
    SHONA("X-SHO", "绍纳人", "Shona", "SHO"),

    /** 恩德贝勒人（非国标扩展码 X-NDE） */
    NDEBELE("X-NDE", "恩德贝勒人", "Ndebele", "NDE"),

    /** 卢奥人（非国标扩展码 X-LUO） */
    LUO("X-LUO", "卢奥人", "Luo", "LUO"),

    /** 基库尤人（非国标扩展码 X-KKY） */
    KIKUYU("X-KKY", "基库尤人", "Kikuyu", "KKY"),

    /** 卡伦金人（非国标扩展码 X-KLN） */
    KALENJIN("X-KLN", "卡伦金人", "Kalenjin", "KLN"),

    /** 马赛人（非国标扩展码 X-MAS） */
    MAASAI("X-MAS", "马赛人", "Maasai", "MAS"),

    /** 阿马齐格人（非国标扩展码 X-AMZ） */
    AMAZIGH("X-AMZ", "阿马齐格人", "Amazigh", "AMZ"),

    /** 图阿雷格人（非国标扩展码 X-TUA） */
    TUAREG("X-TUA", "图阿雷格人", "Tuareg", "TUA"),

    /** 贝都因人（非国标扩展码 X-BEO） */
    BEDOUIN("X-BEO", "贝都因人", "Bedouin", "BEO"),

    /** 丁卡人（非国标扩展码 X-DIN） */
    DINKA("X-DIN", "丁卡人", "Dinka", "DIN"),

    /** 努尔人（非国标扩展码 X-NUER；旧三字码为四字符） */
    NUER("X-NUER", "努尔人", "Nuer", "NUER"),

    /** 阿兹特克人（非国标扩展码 X-AZT） */
    AZTEC("X-AZT", "阿兹特克人", "Aztec", "AZT"),

    /** 玛雅人（非国标扩展码 X-MAY） */
    MAYA("X-MAY", "玛雅人", "Maya", "MAY"),

    /** 印加人（非国标扩展码 X-INC） */
    INCA("X-INC", "印加人", "Inca", "INC"),

    /** 克丘亚人（非国标扩展码 X-QUE） */
    QUECHUA("X-QUE", "克丘亚人", "Quechua", "QUE"),

    /** 艾马拉人（非国标扩展码 X-AYM） */
    AYMARA("X-AYM", "艾马拉人", "Aymara", "AYM"),

    /** 瓜拉尼人（非国标扩展码 X-GUA） */
    GUARANI("X-GUA", "瓜拉尼人", "Guarani", "GUA"),

    /** 马普切人（非国标扩展码 X-MAP） */
    MAPUCHE("X-MAP", "马普切人", "Mapuche", "MAP"),

    /** 阿帕奇人（非国标扩展码 X-APA） */
    APACHE("X-APA", "阿帕奇人", "Apache", "APA"),

    /** 纳瓦霍人（非国标扩展码 X-NAV） */
    NAVAJO("X-NAV", "纳瓦霍人", "Navajo", "NAV"),

    /** 切诺基人（非国标扩展码 X-CHC） */
    CHEROKEE("X-CHC", "切诺基人", "Cherokee", "CHC"),

    /** 苏族人（非国标扩展码 X-SIO） */
    SIOUX("X-SIO", "苏族人", "Sioux", "SIO"),

    /** 克里人（非国标扩展码 X-CRE） */
    CREE("X-CRE", "克里人", "Cree", "CRE"),

    /** 罗姆人（非国标扩展码 X-ROM） */
    ROMANI("X-ROM", "罗姆人", "Romani", "ROM"),

    /** 亚美尼亚人（非国标扩展码 X-ARM） */
    ARMENIAN("X-ARM", "亚美尼亚人", "Armenian", "ARM"),

    /** 库尔德人（非国标扩展码 X-KUR） */
    KURDISH("X-KUR", "库尔德人", "Kurdish", "KUR"),

    /** 犹太人（非国标扩展码 X-JEW） */
    JEWISH("X-JEW", "犹太人", "Jewish", "JEW"),
    ;

    /** GB/T 3304 标准码 */
    private final String code;

    /** 中文名称 */
    private final String label;

    /** 英文名称 */
    private final String enName;

    /** 旧库自定义三字码（非标准，仅供历史数据迁移） */
    private final String legacyCode;

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
     * 严格查找：按 GB/T 3304 码值或枚举常量名匹配，未匹配抛
     * {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link EthnicGroup}
     */
    public static EthnicGroup of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：按 GB/T 3304 码值或枚举常量名匹配
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

    /**
     * 严格查找：按旧库三字码匹配（非标准，仅供历史数据迁移），未匹配抛
     * {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param legacyCode 旧库三字码（不区分大小写）
     * @return {@link EthnicGroup}
     */
    public static EthnicGroup ofLegacy(String legacyCode) {
        return ofLegacyNullable(legacyCode)
                .orElseThrow(() -> new com.asialjim.standard4j.exception.BusinessException(
                        com.asialjim.standard4j.exception.SystemResCode.UNMATCHED_CODE,
                        "未知的民族旧码: [" + legacyCode + "]"));
    }

    /**
     * 宽容查找：按旧库三字码匹配（不区分大小写，容忍首尾空白）
     *
     * @param legacyCode 旧库三字码
     * @return {@link Optional}&lt;{@link EthnicGroup}&gt;
     */
    public static Optional<EthnicGroup> ofLegacyNullable(String legacyCode) {
        if (legacyCode == null || legacyCode.trim().isEmpty())
            return Optional.empty();
        String trimmed = legacyCode.trim();
        for (EthnicGroup value : values()) {
            if (value.legacyCode.equalsIgnoreCase(trimmed))
                return Optional.of(value);
        }
        return Optional.empty();
    }

    /**
     * 宽容解析：按旧库三字码匹配，未匹配一律返回 {@link #UNKNOWN}
     *
     * @param legacyCode 旧库三字码
     * @return {@link EthnicGroup}
     */
    public static EthnicGroup ofLegacyOrDefault(String legacyCode) {
        return ofLegacyNullable(legacyCode).orElse(UNKNOWN);
    }
}
