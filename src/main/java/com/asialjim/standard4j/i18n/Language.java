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

package com.asialjim.standard4j.i18n;

import com.asialjim.standard4j.code.Code;
import com.asialjim.standard4j.code.Codes;

import java.util.Optional;

/**
 * 语言 —— 依据 <b>ISO 639-1</b> 两字母语种码，收录常用语言子集：
 * <b>总使用人数（母语 + 第二语言）超过 5000 万</b>的语言（人数口径参照 Ethnologue，取约数）。
 *
 * <p>说明：</p>
 * <ul>
 *     <li>汉语按宏观语种 {@code zh} 单列（粤语/吴语等变体无 ISO 639-1 码，不单列）；</li>
 *     <li>爪哇语等超 5000 万使用者的语言无 ISO 639-1 码（仅有 639-2/3 码），不在本枚举收录；</li>
 *     <li>本枚举为常用子集而非全量，如需小语种请自行扩展实现或经业务自定义枚举补充。</li>
 * </ul>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum Language implements Code {

    /** 未知/未提供（无标准码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知", "Unknown"),

    /** 汉语（ISO 639-1: zh；总使用人数约 14 亿） */
    CHINESE("zh", "汉语", "Chinese"),

    /** 英语（ISO 639-1: en；总使用人数约 15 亿） */
    ENGLISH("en", "英语", "English"),

    /** 印地语（ISO 639-1: hi） */
    HINDI("hi", "印地语", "Hindi"),

    /** 西班牙语（ISO 639-1: es） */
    SPANISH("es", "西班牙语", "Spanish"),

    /** 法语（ISO 639-1: fr） */
    FRENCH("fr", "法语", "French"),

    /** 阿拉伯语（ISO 639-1: ar，宏观语种） */
    ARABIC("ar", "阿拉伯语", "Arabic"),

    /** 孟加拉语（ISO 639-1: bn） */
    BENGALI("bn", "孟加拉语", "Bengali"),

    /** 葡萄牙语（ISO 639-1: pt） */
    PORTUGUESE("pt", "葡萄牙语", "Portuguese"),

    /** 俄语（ISO 639-1: ru） */
    RUSSIAN("ru", "俄语", "Russian"),

    /** 乌尔都语（ISO 639-1: ur） */
    URDU("ur", "乌尔都语", "Urdu"),

    /** 印尼语（ISO 639-1: id） */
    INDONESIAN("id", "印尼语", "Indonesian"),

    /** 德语（ISO 639-1: de） */
    GERMAN("de", "德语", "German"),

    /** 日语（ISO 639-1: ja） */
    JAPANESE("ja", "日语", "Japanese"),

    /** 斯瓦希里语（ISO 639-1: sw；母语少但二语使用者过亿） */
    SWAHILI("sw", "斯瓦希里语", "Swahili"),

    /** 马拉地语（ISO 639-1: mr） */
    MARATHI("mr", "马拉地语", "Marathi"),

    /** 泰卢固语（ISO 639-1: te） */
    TELUGU("te", "泰卢固语", "Telugu"),

    /** 泰米尔语（ISO 639-1: ta） */
    TAMIL("ta", "泰米尔语", "Tamil"),

    /** 土耳其语（ISO 639-1: tr） */
    TURKISH("tr", "土耳其语", "Turkish"),

    /** 越南语（ISO 639-1: vi） */
    VIETNAMESE("vi", "越南语", "Vietnamese"),

    /** 朝鲜语/韩语（ISO 639-1: ko） */
    KOREAN("ko", "朝鲜语", "Korean"),

    /** 意大利语（ISO 639-1: it） */
    ITALIAN("it", "意大利语", "Italian"),

    /** 波斯语（ISO 639-1: fa） */
    PERSIAN("fa", "波斯语", "Persian"),

    /** 豪萨语（ISO 639-1: ha；二语使用者众多） */
    HAUSA("ha", "豪萨语", "Hausa"),

    /** 旁遮普语（ISO 639-1: pa） */
    PUNJABI("pa", "旁遮普语", "Punjabi"),
    ;

    /** ISO 639-1 两字母语种码 */
    private final String code;

    /** 中文名称 */
    private final String label;

    /** 英文名称 */
    private final String enName;

    Language(String code, String label, String enName) {
        this.code = code;
        this.label = label;
        this.enName = enName;
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
     * 英文名称
     *
     * @return 英文名称
     */
    public String enName() {
        return this.enName;
    }

    /**
     * UNKNOWN 项静态访问器
     *
     * @return {@link #UNKNOWN}
     */
    public static Language unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 语种码或枚举常量名（不区分大小写）
     * @return {@link Language}
     */
    public static Language of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 语种码或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link Language}&gt;
     */
    public static Optional<Language> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 语种码或枚举常量名（不区分大小写）
     * @return {@link Language}
     */
    public static Language ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
