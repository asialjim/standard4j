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
 * 学位 —— 依据 <b>GB/T 6864-2003《中华人民共和国学位代码》</b>：两层层次码。
 *
 * <p>第一层 1 位数字表示学位级别：{@code 1}名誉博士、{@code 2}博士、{@code 3}硕士、{@code 4}学士；
 * 第二层 2 位数字：{@code 01~40} 按学科门类（12 个门类：哲学01/经济学02/法学03/教育学04/文学05/
 * 历史学06/理学07/工学08/农学09/医学10/军事学11/管理学12），{@code 41~80} 按专业学位类型
 * （2003 文本收录 16 个专业学位：博士 245/248/250，硕士 341-352，学士 444）。</p>
 *
 * <p>注：2003 文本之后国务院学位委员会新增的学位类型（如艺术学 213/313/413、
 * 金融硕士 353 等）未纳入本枚举，需求出现时按官方补充文件增补。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum Degree implements Code {

    /** 未知/未提供（无标准码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知"),

    /** 名誉博士（GB/T 6864: 1） */
    HONORARY_DOCTORATE("1", "名誉博士"),

    /** 博士（GB/T 6864: 2，大类） */
    DOCTORATE("2", "博士"),

    /** 哲学博士（GB/T 6864: 201） */
    PHILOSOPHY_DOCTOR("201", "哲学博士"),

    /** 经济学博士（GB/T 6864: 202） */
    ECONOMICS_DOCTOR("202", "经济学博士"),

    /** 法学博士（GB/T 6864: 203） */
    LAW_DOCTOR("203", "法学博士"),

    /** 教育学博士（GB/T 6864: 204） */
    EDUCATION_DOCTOR("204", "教育学博士"),

    /** 文学博士（GB/T 6864: 205） */
    LITERATURE_DOCTOR("205", "文学博士"),

    /** 历史学博士（GB/T 6864: 206） */
    HISTORY_DOCTOR("206", "历史学博士"),

    /** 理学博士（GB/T 6864: 207） */
    SCIENCE_DOCTOR("207", "理学博士"),

    /** 工学博士（GB/T 6864: 208） */
    ENGINEERING_DOCTOR("208", "工学博士"),

    /** 农学博士（GB/T 6864: 209） */
    AGRICULTURE_DOCTOR("209", "农学博士"),

    /** 医学博士（GB/T 6864: 210） */
    MEDICINE_DOCTOR("210", "医学博士"),

    /** 军事学博士（GB/T 6864: 211） */
    MILITARY_SCIENCE_DOCTOR("211", "军事学博士"),

    /** 管理学博士（GB/T 6864: 212） */
    MANAGEMENT_DOCTOR("212", "管理学博士"),

    /** 临床医学博士专业学位（GB/T 6864: 245） */
    CLINICAL_MEDICINE_DOCTOR_PRO("245", "临床医学博士专业学位"),

    /** 兽医博士专业学位（GB/T 6864: 248） */
    VETERINARY_DOCTOR_PRO("248", "兽医博士专业学位"),

    /** 口腔医学博士专业学位（GB/T 6864: 250） */
    STOMATOLOGY_DOCTOR_PRO("250", "口腔医学博士专业学位"),

    /** 硕士（GB/T 6864: 3，大类） */
    MASTER("3", "硕士"),

    /** 哲学硕士（GB/T 6864: 301） */
    PHILOSOPHY_MASTER("301", "哲学硕士"),

    /** 经济学硕士（GB/T 6864: 302） */
    ECONOMICS_MASTER("302", "经济学硕士"),

    /** 法学硕士（GB/T 6864: 303） */
    LAW_MASTER("303", "法学硕士"),

    /** 教育学硕士（GB/T 6864: 304） */
    EDUCATION_MASTER("304", "教育学硕士"),

    /** 文学硕士（GB/T 6864: 305） */
    LITERATURE_MASTER("305", "文学硕士"),

    /** 历史学硕士（GB/T 6864: 306） */
    HISTORY_MASTER("306", "历史学硕士"),

    /** 理学硕士（GB/T 6864: 307） */
    SCIENCE_MASTER("307", "理学硕士"),

    /** 工学硕士（GB/T 6864: 308） */
    ENGINEERING_MASTER("308", "工学硕士"),

    /** 农学硕士（GB/T 6864: 309） */
    AGRICULTURE_MASTER("309", "农学硕士"),

    /** 医学硕士（GB/T 6864: 310） */
    MEDICINE_MASTER("310", "医学硕士"),

    /** 军事学硕士（GB/T 6864: 311） */
    MILITARY_SCIENCE_MASTER("311", "军事学硕士"),

    /** 管理学硕士（GB/T 6864: 312） */
    MANAGEMENT_MASTER("312", "管理学硕士"),

    /** 法律硕士专业学位（GB/T 6864: 341） */
    LAW_MASTER_PRO("341", "法律硕士专业学位"),

    /** 教育硕士专业学位（GB/T 6864: 342） */
    EDUCATION_MASTER_PRO("342", "教育硕士专业学位"),

    /** 工程硕士专业学位（GB/T 6864: 343） */
    ENGINEERING_MASTER_PRO("343", "工程硕士专业学位"),

    /** 建筑学硕士专业学位（GB/T 6864: 344） */
    ARCHITECTURE_MASTER_PRO("344", "建筑学硕士专业学位"),

    /** 临床医学硕士专业学位（GB/T 6864: 345） */
    CLINICAL_MEDICINE_MASTER_PRO("345", "临床医学硕士专业学位"),

    /** 工商管理硕士专业学位（GB/T 6864: 346） */
    BUSINESS_ADMINISTRATION_MASTER_PRO("346", "工商管理硕士专业学位"),

    /** 农业推广硕士专业学位（GB/T 6864: 347） */
    AGRICULTURE_EXTENSION_MASTER_PRO("347", "农业推广硕士专业学位"),

    /** 兽医硕士专业学位（GB/T 6864: 348） */
    VETERINARY_MASTER_PRO("348", "兽医硕士专业学位"),

    /** 公共管理硕士专业学位（GB/T 6864: 349） */
    PUBLIC_ADMINISTRATION_MASTER_PRO("349", "公共管理硕士专业学位"),

    /** 口腔医学硕士专业学位（GB/T 6864: 350） */
    STOMATOLOGY_MASTER_PRO("350", "口腔医学硕士专业学位"),

    /** 公共卫生硕士专业学位（GB/T 6864: 351） */
    PUBLIC_HEALTH_MASTER_PRO("351", "公共卫生硕士专业学位"),

    /** 军事硕士专业学位（GB/T 6864: 352） */
    MILITARY_MASTER_PRO("352", "军事硕士专业学位"),

    /** 学士（GB/T 6864: 4，大类） */
    BACHELOR("4", "学士"),

    /** 哲学学士（GB/T 6864: 401） */
    PHILOSOPHY_BACHELOR("401", "哲学学士"),

    /** 经济学学士（GB/T 6864: 402） */
    ECONOMICS_BACHELOR("402", "经济学学士"),

    /** 法学学士（GB/T 6864: 403） */
    LAW_BACHELOR("403", "法学学士"),

    /** 教育学学士（GB/T 6864: 404） */
    EDUCATION_BACHELOR("404", "教育学学士"),

    /** 文学学士（GB/T 6864: 405） */
    LITERATURE_BACHELOR("405", "文学学士"),

    /** 历史学学士（GB/T 6864: 406） */
    HISTORY_BACHELOR("406", "历史学学士"),

    /** 理学学士（GB/T 6864: 407） */
    SCIENCE_BACHELOR("407", "理学学士"),

    /** 工学学士（GB/T 6864: 408） */
    ENGINEERING_BACHELOR("408", "工学学士"),

    /** 农学学士（GB/T 6864: 409） */
    AGRICULTURE_BACHELOR("409", "农学学士"),

    /** 医学学士（GB/T 6864: 410） */
    MEDICINE_BACHELOR("410", "医学学士"),

    /** 军事学学士（GB/T 6864: 411） */
    MILITARY_SCIENCE_BACHELOR("411", "军事学学士"),

    /** 管理学学士（GB/T 6864: 412） */
    MANAGEMENT_BACHELOR("412", "管理学学士"),

    /** 建筑学学士专业学位（GB/T 6864: 444） */
    ARCHITECTURE_BACHELOR_PRO("444", "建筑学学士专业学位"),
    ;

    private final String code;
    private final String label;

    Degree(String code, String label) {
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
    public static Degree unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Degree}
     */
    public static Degree of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link Degree}&gt;
     */
    public static Optional<Degree> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Degree}
     */
    public static Degree ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
