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
 * 身份证件类型。
 *
 * <p><b>对齐说明</b>：证件类型代码无 GB 国标覆盖，主码采用
 * <b>GA/T 2000.156-2016《公安信息代码 第156部分：常用证件代码》</b>口径（码表待持标准全文逐项核对）；
 * 内置 <b>JR/T 0160-2018</b>（金融行业 DM001 代码表）别名码 {@link #getFinancialCode()}，金融场景直接可用；
 * 其他行业标准码值可经别名映射外挂。</p>
 *
 * <p>每个证件类型自带脱敏保留位数（{@link #getSensitiveLeft()}/{@link #getSensitiveRight()}），
 * 供脱敏规则元数据使用；脱敏执行归敏感信息处理库。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum IdCardType implements Code {

    /** 未知/未提供（无标准码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知", "Unknown", null, 0, 0),

    /** 居民身份证（GA/T: 01；JR/T: 1） */
    RESIDENT_ID_CARD("01", "居民身份证", "Resident Identity Card", "1", 6, 4),

    /** 户口簿（GA/T: 02；JR/T: 5） */
    HOUSEHOLD_REGISTER("02", "户口簿", "Household Register", "5", 6, 4),

    /** 驾驶证（GA/T: 03；JR/T: 11） */
    DRIVER_LICENSE("03", "驾驶证", "Driver License", "11", 6, 4),

    /** 社会保障卡（GA/T: 04；JR/T 未定义） */
    SOCIAL_SECURITY_CARD("04", "社会保障卡", "Social Security Card", null, 6, 4),

    /** 军官证（GA/T: 05；JR/T: 2） */
    MILITARY_ID("05", "军官证", "Military ID", "2", 1, 1),

    /** 警官证（GA/T: 06；JR/T: 3） */
    POLICE_OFFICER_ID("06", "警官证", "Police Officer ID", "3", 1, 1),

    /** 公务员证（GA/T: 07；JR/T 未定义） */
    OFFICER_ID("07", "公务员证", "Officer ID", null, 1, 1),

    /** 港澳通行证（GA/T: 08；JR/T: 8） */
    HK_MO_PASS("08", "港澳通行证", "Hong Kong & Macao Pass", "8", 3, 3),

    /** 台湾通行证（GA/T: 09；JR/T: 7） */
    TW_PASS("09", "台湾通行证", "Taiwan Pass", "7", 3, 3),

    /** 边境通行证（GA/T: 10；JR/T 未定义） */
    BORDER_PASS("10", "边境通行证", "Border Pass", null, 3, 3),

    /** 海员证（GA/T: 11；JR/T 未定义） */
    SEAMAN_BOOK("11", "海员证", "Seaman Book", null, 3, 3),

    /** 律师执业证（GA/T: 12；JR/T 未定义） */
    LAWYER_LICENSE("12", "律师执业证", "Lawyer License", null, 3, 3),

    /** 外国人居住证（GA/T: 13；JR/T 未定义） */
    FOREIGN_RESIDENCE_PERMIT("13", "外国人居住证", "Foreign Residence Permit", null, 3, 3),

    /** 护照（GA/T: 99；JR/T: 6） */
    PASSPORT("99", "护照", "Passport", "6", 6, 4),
    ;

    /** GA/T 2000.156-2016 主码 */
    private final String code;

    /** 中文名称 */
    private final String label;

    /** 英文名称 */
    private final String enName;

    /** JR/T 0160-2018（金融 DM001）别名码；该标准未定义时为 {@code null} */
    private final String financialCode;

    /** 证件号脱敏保留左边字符数 */
    private final int sensitiveLeft;

    /** 证件号脱敏保留右边字符数 */
    private final int sensitiveRight;

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
    public static IdCardType unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link IdCardType}
     */
    public static IdCardType of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link IdCardType}&gt;
     */
    public static Optional<IdCardType> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 码值或枚举常量名（不区分大小写）
     * @return {@link IdCardType}
     */
    public static IdCardType ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }
}
