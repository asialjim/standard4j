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
 * 学历 —— 依据 <b>GB/T 4658-2006《学历代码》</b>：2 位层次码，
 * 末位数字按学业状态：{@code *1}=毕业、{@code *2}=结业、{@code *3}=肄业。
 *
 * <p>层次：10-19 研究生教育、20-28 大学本科教育、30-33 大学专科教育、40-49 中等职业教育、
 * 60-63 普通高级中学教育、70-73 初级中学教育、80-83 小学教育、90 其他。</p>
 *
 * <p>注：部分业务系统存在私有扩展（如 34/35、91-93、99），本枚举以标准文本为准，不收录私有扩展。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public enum Education implements Code {

    /** 其他（GB/T 4658: 90）—— 兼任本字典 UNKNOWN */
    UNKNOWN("90", "其他"),

    /** 研究生教育（GB/T 4658: 10，大类） */
    GRADUATE_EDU("10", "研究生教育"),

    /** 博士研究生毕业（GB/T 4658: 11） */
    DOCTOR_GRADUATED("11", "博士研究生毕业"),

    /** 博士研究生结业（GB/T 4658: 12） */
    DOCTOR_COMPLETED("12", "博士研究生结业"),

    /** 博士研究生肄业（GB/T 4658: 13） */
    DOCTOR_UNFINISHED("13", "博士研究生肄业"),

    /** 硕士研究生毕业（GB/T 4658: 14） */
    MASTER_GRADUATED("14", "硕士研究生毕业"),

    /** 硕士研究生结业（GB/T 4658: 15） */
    MASTER_COMPLETED("15", "硕士研究生结业"),

    /** 硕士研究生肄业（GB/T 4658: 16） */
    MASTER_UNFINISHED("16", "硕士研究生肄业"),

    /** 研究生班毕业（GB/T 4658: 17） */
    GRAD_CLASS_GRADUATED("17", "研究生班毕业"),

    /** 研究生班结业（GB/T 4658: 18） */
    GRAD_CLASS_COMPLETED("18", "研究生班结业"),

    /** 研究生班肄业（GB/T 4658: 19） */
    GRAD_CLASS_UNFINISHED("19", "研究生班肄业"),

    /** 大学本科教育（GB/T 4658: 20，大类） */
    UNDERGRAD_EDU("20", "大学本科教育"),

    /** 大学本科毕业（GB/T 4658: 21） */
    UNDERGRAD_GRADUATED("21", "大学本科毕业"),

    /** 大学本科结业（GB/T 4658: 22） */
    UNDERGRAD_COMPLETED("22", "大学本科结业"),

    /** 大学本科肄业（GB/T 4658: 23） */
    UNDERGRAD_UNFINISHED("23", "大学本科肄业"),

    /** 大学普通班毕业（GB/T 4658: 28，1970-1976 年入普通高校的毕业生） */
    ORDINARY_COLLEGE_CLASS("28", "大学普通班毕业"),

    /** 大学专科教育（GB/T 4658: 30，大类；含高等专科、高等职业教育） */
    COLLEGE_EDU("30", "大学专科教育"),

    /** 大学专科毕业（GB/T 4658: 31） */
    COLLEGE_GRADUATED("31", "大学专科毕业"),

    /** 大学专科结业（GB/T 4658: 32） */
    COLLEGE_COMPLETED("32", "大学专科结业"),

    /** 大学专科肄业（GB/T 4658: 33） */
    COLLEGE_UNFINISHED("33", "大学专科肄业"),

    /** 中等职业教育（GB/T 4658: 40，大类；含中等师范学校教育） */
    SECONDARY_VOCATIONAL_EDU("40", "中等职业教育"),

    /** 中等专科毕业（GB/T 4658: 41） */
    SECONDARY_SPECIAL_GRADUATED("41", "中等专科毕业"),

    /** 中等专科结业（GB/T 4658: 42） */
    SECONDARY_SPECIAL_COMPLETED("42", "中等专科结业"),

    /** 中等专科肄业（GB/T 4658: 43） */
    SECONDARY_SPECIAL_UNFINISHED("43", "中等专科肄业"),

    /** 职业高中毕业（GB/T 4658: 44） */
    VOCATIONAL_HIGH_GRADUATED("44", "职业高中毕业"),

    /** 职业高中结业（GB/T 4658: 45） */
    VOCATIONAL_HIGH_COMPLETED("45", "职业高中结业"),

    /** 职业高中肄业（GB/T 4658: 46） */
    VOCATIONAL_HIGH_UNFINISHED("46", "职业高中肄业"),

    /** 技工学校毕业（GB/T 4658: 47） */
    TECHNICAL_SCHOOL_GRADUATED("47", "技工学校毕业"),

    /** 技工学校结业（GB/T 4658: 48） */
    TECHNICAL_SCHOOL_COMPLETED("48", "技工学校结业"),

    /** 技工学校肄业（GB/T 4658: 49） */
    TECHNICAL_SCHOOL_UNFINISHED("49", "技工学校肄业"),

    /** 普通高级中学教育（GB/T 4658: 60，大类） */
    SENIOR_HIGH_EDU("60", "普通高级中学教育"),

    /** 普通高中毕业（GB/T 4658: 61） */
    SENIOR_HIGH_GRADUATED("61", "普通高中毕业"),

    /** 普通高中结业（GB/T 4658: 62） */
    SENIOR_HIGH_COMPLETED("62", "普通高中结业"),

    /** 普通高中肄业（GB/T 4658: 63） */
    SENIOR_HIGH_UNFINISHED("63", "普通高中肄业"),

    /** 初级中学教育（GB/T 4658: 70，大类） */
    JUNIOR_HIGH_EDU("70", "初级中学教育"),

    /** 初中毕业（GB/T 4658: 71） */
    JUNIOR_HIGH_GRADUATED("71", "初中毕业"),

    /** 初中结业（GB/T 4658: 72） */
    JUNIOR_HIGH_COMPLETED("72", "初中结业"),

    /** 初中肄业（GB/T 4658: 73） */
    JUNIOR_HIGH_UNFINISHED("73", "初中肄业"),

    /** 小学教育（GB/T 4658: 80，大类） */
    PRIMARY_EDU("80", "小学教育"),

    /** 小学毕业（GB/T 4658: 81） */
    PRIMARY_GRADUATED("81", "小学毕业"),

    /** 小学结业（GB/T 4658: 82） */
    PRIMARY_COMPLETED("82", "小学结业"),

    /** 小学肄业（GB/T 4658: 83） */
    PRIMARY_UNFINISHED("83", "小学肄业"),
    ;

    private final String code;
    private final String label;

    Education(String code, String label) {
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
    public static Education unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Education}
     */
    public static Education of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link Education}&gt;
     */
    public static Optional<Education> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Education}
     */
    public static Education ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
