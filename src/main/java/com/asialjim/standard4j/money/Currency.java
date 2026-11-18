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

import com.asialjim.standard4j.code.Code;
import com.asialjim.standard4j.code.Codes;
import lombok.Getter;

import java.util.Optional;

/**
 * 货币 —— 依据 <b>ISO 4217《货币与资金代码》</b>（2024-06-25 官方发布版）：全量收录
 * 179 个现行有效代码（三位字母码 + 三位数字码 + 最小货币单位精度），
 * 含资金类代码（XAU 黄金、XDR 特别提款权等，精度 {@code -1} 表示无小数）。
 *
 * <p>中英文名取自 CLDR；UYW/ZWG 为 2024 新增码，zh 译名待官方数据校准。</p>
 *
 * <p><b>注意区分两个"未知"</b>：{@link #UNKNOWN} 是本库约定的"未提供/解析失败"兜底项（码值 {@code "-"}，
 * 非标准码）；{@link #XXX}（数字码 999）是 ISO 4217 官方定义的"无货币"代码，属于标准码表的一部分，
 * 两者不可互换。</p>
 *
 * <p>字段 {@link #getMinorUnit()} 为最小货币单位精度（主单位下小数位数）：
 * 人民币/美元为 {@code 2}、日元为 {@code 0}、资金类代码为 {@code -1}。
 * {@link Money} 依赖该精度做最小单位换算。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
@Getter
public enum Currency implements Code {

    /** 未知/未提供（无标准码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知", "Unknown", "-", 0),

        /** 阿联酋迪拉姆（ISO 4217: AED / 784；精度 2；JDK CLDR） */
    AED("AED", "阿联酋迪拉姆", "United Arab Emirates Dirham", "784", 2),
    /** 阿富汗尼（ISO 4217: AFN / 971；精度 2；JDK CLDR） */
    AFN("AFN", "阿富汗尼", "Afghan Afghani", "971", 2),
    /** 阿尔巴尼亚列克（ISO 4217: ALL / 008；精度 2；JDK CLDR） */
    ALL("ALL", "阿尔巴尼亚列克", "Albanian Lek", "008", 2),
    /** 亚美尼亚德拉姆（ISO 4217: AMD / 051；精度 2；JDK CLDR） */
    AMD("AMD", "亚美尼亚德拉姆", "Armenian Dram", "051", 2),
    /** 荷属安的列斯盾（ISO 4217: ANG / 532；精度 2；JDK CLDR） */
    ANG("ANG", "荷属安的列斯盾", "Netherlands Antillean Guilder", "532", 2),
    /** 安哥拉宽扎（ISO 4217: AOA / 973；精度 2；JDK CLDR） */
    AOA("AOA", "安哥拉宽扎", "Angolan Kwanza", "973", 2),
    /** 阿根廷比索（ISO 4217: ARS / 032；精度 2；JDK CLDR） */
    ARS("ARS", "阿根廷比索", "Argentine Peso", "032", 2),
    /** 澳大利亚元（ISO 4217: AUD / 036；精度 2；JDK CLDR） */
    AUD("AUD", "澳大利亚元", "Australian Dollar", "036", 2),
    /** 阿鲁巴弗罗林（ISO 4217: AWG / 533；精度 2；JDK CLDR） */
    AWG("AWG", "阿鲁巴弗罗林", "Aruban Florin", "533", 2),
    /** 阿塞拜疆马纳特（ISO 4217: AZN / 944；精度 2；JDK CLDR） */
    AZN("AZN", "阿塞拜疆马纳特", "Azerbaijani Manat", "944", 2),
    /** 波斯尼亚-黑塞哥维那可兑换马克（ISO 4217: BAM / 977；精度 2；JDK CLDR） */
    BAM("BAM", "波斯尼亚-黑塞哥维那可兑换马克", "Bosnia-Herzegovina Convertible Mark", "977", 2),
    /** 巴巴多斯元（ISO 4217: BBD / 052；精度 2；JDK CLDR） */
    BBD("BBD", "巴巴多斯元", "Barbadian Dollar", "052", 2),
    /** 孟加拉塔卡（ISO 4217: BDT / 050；精度 2；JDK CLDR） */
    BDT("BDT", "孟加拉塔卡", "Bangladeshi Taka", "050", 2),
    /** 保加利亚列弗（ISO 4217: BGN / 975；精度 2；JDK CLDR） */
    BGN("BGN", "保加利亚列弗", "Bulgarian Lev", "975", 2),
    /** 巴林第纳尔（ISO 4217: BHD / 048；精度 3；JDK CLDR） */
    BHD("BHD", "巴林第纳尔", "Bahraini Dinar", "048", 3),
    /** 布隆迪法郎（ISO 4217: BIF / 108；精度 0；JDK CLDR） */
    BIF("BIF", "布隆迪法郎", "Burundian Franc", "108", 0),
    /** 百慕大元（ISO 4217: BMD / 060；精度 2；JDK CLDR） */
    BMD("BMD", "百慕大元", "Bermudan Dollar", "060", 2),
    /** 文莱元（ISO 4217: BND / 096；精度 2；JDK CLDR） */
    BND("BND", "文莱元", "Brunei Dollar", "096", 2),
    /** 玻利维亚诺（ISO 4217: BOB / 068；精度 2；JDK CLDR） */
    BOB("BOB", "玻利维亚诺", "Bolivian Boliviano", "068", 2),
    /** 玻利维亚 Mvdol（资金）（ISO 4217: BOV / 984；精度 2；JDK CLDR） */
    BOV("BOV", "玻利维亚 Mvdol（资金）", "Bolivian Mvdol", "984", 2),
    /** 巴西雷亚尔（ISO 4217: BRL / 986；精度 2；JDK CLDR） */
    BRL("BRL", "巴西雷亚尔", "Brazilian Real", "986", 2),
    /** 巴哈马元（ISO 4217: BSD / 044；精度 2；JDK CLDR） */
    BSD("BSD", "巴哈马元", "Bahamian Dollar", "044", 2),
    /** 不丹努尔特鲁姆（ISO 4217: BTN / 064；精度 2；JDK CLDR） */
    BTN("BTN", "不丹努尔特鲁姆", "Bhutanese Ngultrum", "064", 2),
    /** 博茨瓦纳普拉（ISO 4217: BWP / 072；精度 2；JDK CLDR） */
    BWP("BWP", "博茨瓦纳普拉", "Botswanan Pula", "072", 2),
    /** 白俄罗斯卢布（ISO 4217: BYN / 933；精度 2；JDK CLDR） */
    BYN("BYN", "白俄罗斯卢布", "Belarusian Ruble", "933", 2),
    /** 伯利兹元（ISO 4217: BZD / 084；精度 2；JDK CLDR） */
    BZD("BZD", "伯利兹元", "Belize Dollar", "084", 2),
    /** 加拿大元（ISO 4217: CAD / 124；精度 2；JDK CLDR） */
    CAD("CAD", "加拿大元", "Canadian Dollar", "124", 2),
    /** 刚果法郎（ISO 4217: CDF / 976；精度 2；JDK CLDR） */
    CDF("CDF", "刚果法郎", "Congolese Franc", "976", 2),
    /** 欧元 (WIR)（ISO 4217: CHE / 947；精度 2；JDK CLDR） */
    CHE("CHE", "欧元 (WIR)", "WIR Euro", "947", 2),
    /** 瑞士法郎（ISO 4217: CHF / 756；精度 2；JDK CLDR） */
    CHF("CHF", "瑞士法郎", "Swiss Franc", "756", 2),
    /** 法郎 (WIR)（ISO 4217: CHW / 948；精度 2；JDK CLDR） */
    CHW("CHW", "法郎 (WIR)", "WIR Franc", "948", 2),
    /** 智利（资金）（ISO 4217: CLF / 990；精度 4；JDK CLDR） */
    CLF("CLF", "智利（资金）", "Chilean Unit of Account (UF)", "990", 4),
    /** 智利比索（ISO 4217: CLP / 152；精度 0；JDK CLDR） */
    CLP("CLP", "智利比索", "Chilean Peso", "152", 0),
    /** 人民币（ISO 4217: CNY / 156；精度 2；JDK CLDR） */
    CNY("CNY", "人民币", "Chinese Yuan", "156", 2),
    /** 哥伦比亚比索（ISO 4217: COP / 170；精度 2；JDK CLDR） */
    COP("COP", "哥伦比亚比索", "Colombian Peso", "170", 2),
    /** 哥伦比亚币（ISO 4217: COU / 970；精度 2；JDK CLDR） */
    COU("COU", "哥伦比亚币", "Colombian Real Value Unit", "970", 2),
    /** 哥斯达黎加科朗（ISO 4217: CRC / 188；精度 2；JDK CLDR） */
    CRC("CRC", "哥斯达黎加科朗", "Costa Rican Colón", "188", 2),
    /** 古巴可兑换比索（ISO 4217: CUC / 931；精度 2；JDK CLDR） */
    CUC("CUC", "古巴可兑换比索", "Cuban Convertible Peso", "931", 2),
    /** 古巴比索（ISO 4217: CUP / 192；精度 2；JDK CLDR） */
    CUP("CUP", "古巴比索", "Cuban Peso", "192", 2),
    /** 佛得角埃斯库多（ISO 4217: CVE / 132；精度 2；JDK CLDR） */
    CVE("CVE", "佛得角埃斯库多", "Cape Verdean Escudo", "132", 2),
    /** 捷克克朗（ISO 4217: CZK / 203；精度 2；JDK CLDR） */
    CZK("CZK", "捷克克朗", "Czech Koruna", "203", 2),
    /** 吉布提法郎（ISO 4217: DJF / 262；精度 0；JDK CLDR） */
    DJF("DJF", "吉布提法郎", "Djiboutian Franc", "262", 0),
    /** 丹麦克朗（ISO 4217: DKK / 208；精度 2；JDK CLDR） */
    DKK("DKK", "丹麦克朗", "Danish Krone", "208", 2),
    /** 多米尼加比索（ISO 4217: DOP / 214；精度 2；JDK CLDR） */
    DOP("DOP", "多米尼加比索", "Dominican Peso", "214", 2),
    /** 阿尔及利亚第纳尔（ISO 4217: DZD / 012；精度 2；JDK CLDR） */
    DZD("DZD", "阿尔及利亚第纳尔", "Algerian Dinar", "012", 2),
    /** 埃及镑（ISO 4217: EGP / 818；精度 2；JDK CLDR） */
    EGP("EGP", "埃及镑", "Egyptian Pound", "818", 2),
    /** 厄立特里亚纳克法（ISO 4217: ERN / 232；精度 2；JDK CLDR） */
    ERN("ERN", "厄立特里亚纳克法", "Eritrean Nakfa", "232", 2),
    /** 埃塞俄比亚比尔（ISO 4217: ETB / 230；精度 2；JDK CLDR） */
    ETB("ETB", "埃塞俄比亚比尔", "Ethiopian Birr", "230", 2),
    /** 欧元（ISO 4217: EUR / 978；精度 2；JDK CLDR） */
    EUR("EUR", "欧元", "Euro", "978", 2),
    /** 斐济元（ISO 4217: FJD / 242；精度 2；JDK CLDR） */
    FJD("FJD", "斐济元", "Fijian Dollar", "242", 2),
    /** 福克兰群岛镑（ISO 4217: FKP / 238；精度 2；JDK CLDR） */
    FKP("FKP", "福克兰群岛镑", "Falkland Islands Pound", "238", 2),
    /** 英镑（ISO 4217: GBP / 826；精度 2；JDK CLDR） */
    GBP("GBP", "英镑", "British Pound", "826", 2),
    /** 格鲁吉亚拉里（ISO 4217: GEL / 981；精度 2；JDK CLDR） */
    GEL("GEL", "格鲁吉亚拉里", "Georgian Lari", "981", 2),
    /** 加纳塞地（ISO 4217: GHS / 936；精度 2；JDK CLDR） */
    GHS("GHS", "加纳塞地", "Ghanaian Cedi", "936", 2),
    /** 直布罗陀镑（ISO 4217: GIP / 292；精度 2；JDK CLDR） */
    GIP("GIP", "直布罗陀镑", "Gibraltar Pound", "292", 2),
    /** 冈比亚达拉西（ISO 4217: GMD / 270；精度 2；JDK CLDR） */
    GMD("GMD", "冈比亚达拉西", "Gambian Dalasi", "270", 2),
    /** 几内亚法郎（ISO 4217: GNF / 324；精度 0；JDK CLDR） */
    GNF("GNF", "几内亚法郎", "Guinean Franc", "324", 0),
    /** 危地马拉格查尔（ISO 4217: GTQ / 320；精度 2；JDK CLDR） */
    GTQ("GTQ", "危地马拉格查尔", "Guatemalan Quetzal", "320", 2),
    /** 圭亚那元（ISO 4217: GYD / 328；精度 2；JDK CLDR） */
    GYD("GYD", "圭亚那元", "Guyanaese Dollar", "328", 2),
    /** 港元（ISO 4217: HKD / 344；精度 2；JDK CLDR） */
    HKD("HKD", "港元", "Hong Kong Dollar", "344", 2),
    /** 洪都拉斯伦皮拉（ISO 4217: HNL / 340；精度 2；JDK CLDR） */
    HNL("HNL", "洪都拉斯伦皮拉", "Honduran Lempira", "340", 2),
    /** 海地古德（ISO 4217: HTG / 332；精度 2；JDK CLDR） */
    HTG("HTG", "海地古德", "Haitian Gourde", "332", 2),
    /** 匈牙利福林（ISO 4217: HUF / 348；精度 2；JDK CLDR） */
    HUF("HUF", "匈牙利福林", "Hungarian Forint", "348", 2),
    /** 印度尼西亚盾（ISO 4217: IDR / 360；精度 2；JDK CLDR） */
    IDR("IDR", "印度尼西亚盾", "Indonesian Rupiah", "360", 2),
    /** 以色列新谢克尔（ISO 4217: ILS / 376；精度 2；JDK CLDR） */
    ILS("ILS", "以色列新谢克尔", "Israeli New Shekel", "376", 2),
    /** 印度卢比（ISO 4217: INR / 356；精度 2；JDK CLDR） */
    INR("INR", "印度卢比", "Indian Rupee", "356", 2),
    /** 伊拉克第纳尔（ISO 4217: IQD / 368；精度 3；JDK CLDR） */
    IQD("IQD", "伊拉克第纳尔", "Iraqi Dinar", "368", 3),
    /** 伊朗里亚尔（ISO 4217: IRR / 364；精度 2；JDK CLDR） */
    IRR("IRR", "伊朗里亚尔", "Iranian Rial", "364", 2),
    /** 冰岛克朗（ISO 4217: ISK / 352；精度 0；JDK CLDR） */
    ISK("ISK", "冰岛克朗", "Icelandic Króna", "352", 0),
    /** 牙买加元（ISO 4217: JMD / 388；精度 2；JDK CLDR） */
    JMD("JMD", "牙买加元", "Jamaican Dollar", "388", 2),
    /** 约旦第纳尔（ISO 4217: JOD / 400；精度 3；JDK CLDR） */
    JOD("JOD", "约旦第纳尔", "Jordanian Dinar", "400", 3),
    /** 日元（ISO 4217: JPY / 392；精度 0；JDK CLDR） */
    JPY("JPY", "日元", "Japanese Yen", "392", 0),
    /** 肯尼亚先令（ISO 4217: KES / 404；精度 2；JDK CLDR） */
    KES("KES", "肯尼亚先令", "Kenyan Shilling", "404", 2),
    /** 吉尔吉斯斯坦索姆（ISO 4217: KGS / 417；精度 2；JDK CLDR） */
    KGS("KGS", "吉尔吉斯斯坦索姆", "Kyrgystani Som", "417", 2),
    /** 柬埔寨瑞尔（ISO 4217: KHR / 116；精度 2；JDK CLDR） */
    KHR("KHR", "柬埔寨瑞尔", "Cambodian Riel", "116", 2),
    /** 科摩罗法郎（ISO 4217: KMF / 174；精度 0；JDK CLDR） */
    KMF("KMF", "科摩罗法郎", "Comorian Franc", "174", 0),
    /** 朝鲜元（ISO 4217: KPW / 408；精度 2；JDK CLDR） */
    KPW("KPW", "朝鲜元", "North Korean Won", "408", 2),
    /** 韩元（ISO 4217: KRW / 410；精度 0；JDK CLDR） */
    KRW("KRW", "韩元", "South Korean Won", "410", 0),
    /** 科威特第纳尔（ISO 4217: KWD / 414；精度 3；JDK CLDR） */
    KWD("KWD", "科威特第纳尔", "Kuwaiti Dinar", "414", 3),
    /** 开曼元（ISO 4217: KYD / 136；精度 2；JDK CLDR） */
    KYD("KYD", "开曼元", "Cayman Islands Dollar", "136", 2),
    /** 哈萨克斯坦坚戈（ISO 4217: KZT / 398；精度 2；JDK CLDR） */
    KZT("KZT", "哈萨克斯坦坚戈", "Kazakhstani Tenge", "398", 2),
    /** 老挝基普（ISO 4217: LAK / 418；精度 2；JDK CLDR） */
    LAK("LAK", "老挝基普", "Laotian Kip", "418", 2),
    /** 黎巴嫩镑（ISO 4217: LBP / 422；精度 2；JDK CLDR） */
    LBP("LBP", "黎巴嫩镑", "Lebanese Pound", "422", 2),
    /** 斯里兰卡卢比（ISO 4217: LKR / 144；精度 2；JDK CLDR） */
    LKR("LKR", "斯里兰卡卢比", "Sri Lankan Rupee", "144", 2),
    /** 利比里亚元（ISO 4217: LRD / 430；精度 2；JDK CLDR） */
    LRD("LRD", "利比里亚元", "Liberian Dollar", "430", 2),
    /** 莱索托洛蒂（ISO 4217: LSL / 426；精度 2；JDK CLDR） */
    LSL("LSL", "莱索托洛蒂", "Lesotho Loti", "426", 2),
    /** 利比亚第纳尔（ISO 4217: LYD / 434；精度 3；JDK CLDR） */
    LYD("LYD", "利比亚第纳尔", "Libyan Dinar", "434", 3),
    /** 摩洛哥迪拉姆（ISO 4217: MAD / 504；精度 2；JDK CLDR） */
    MAD("MAD", "摩洛哥迪拉姆", "Moroccan Dirham", "504", 2),
    /** 摩尔多瓦列伊（ISO 4217: MDL / 498；精度 2；JDK CLDR） */
    MDL("MDL", "摩尔多瓦列伊", "Moldovan Leu", "498", 2),
    /** 马达加斯加阿里亚里（ISO 4217: MGA / 969；精度 2；JDK CLDR） */
    MGA("MGA", "马达加斯加阿里亚里", "Malagasy Ariary", "969", 2),
    /** 马其顿第纳尔（ISO 4217: MKD / 807；精度 2；JDK CLDR） */
    MKD("MKD", "马其顿第纳尔", "Macedonian Denar", "807", 2),
    /** 缅甸元（ISO 4217: MMK / 104；精度 2；JDK CLDR） */
    MMK("MMK", "缅甸元", "Myanmar Kyat", "104", 2),
    /** 蒙古图格里克（ISO 4217: MNT / 496；精度 2；JDK CLDR） */
    MNT("MNT", "蒙古图格里克", "Mongolian Tugrik", "496", 2),
    /** 澳门币（ISO 4217: MOP / 446；精度 2；JDK CLDR） */
    MOP("MOP", "澳门币", "Macanese Pataca", "446", 2),
    /** 毛里塔尼亚乌吉亚（ISO 4217: MRU / 929；精度 2；JDK CLDR） */
    MRU("MRU", "毛里塔尼亚乌吉亚", "Mauritanian Ouguiya", "929", 2),
    /** 毛里求斯卢比（ISO 4217: MUR / 480；精度 2；JDK CLDR） */
    MUR("MUR", "毛里求斯卢比", "Mauritian Rupee", "480", 2),
    /** 马尔代夫卢菲亚（ISO 4217: MVR / 462；精度 2；JDK CLDR） */
    MVR("MVR", "马尔代夫卢菲亚", "Maldivian Rufiyaa", "462", 2),
    /** 马拉维克瓦查（ISO 4217: MWK / 454；精度 2；JDK CLDR） */
    MWK("MWK", "马拉维克瓦查", "Malawian Kwacha", "454", 2),
    /** 墨西哥比索（ISO 4217: MXN / 484；精度 2；JDK CLDR） */
    MXN("MXN", "墨西哥比索", "Mexican Peso", "484", 2),
    /** 墨西哥（资金）（ISO 4217: MXV / 979；精度 2；JDK CLDR） */
    MXV("MXV", "墨西哥（资金）", "Mexican Investment Unit", "979", 2),
    /** 马来西亚林吉特（ISO 4217: MYR / 458；精度 2；JDK CLDR） */
    MYR("MYR", "马来西亚林吉特", "Malaysian Ringgit", "458", 2),
    /** 莫桑比克美提卡（ISO 4217: MZN / 943；精度 2；JDK CLDR） */
    MZN("MZN", "莫桑比克美提卡", "Mozambican Metical", "943", 2),
    /** 纳米比亚元（ISO 4217: NAD / 516；精度 2；JDK CLDR） */
    NAD("NAD", "纳米比亚元", "Namibian Dollar", "516", 2),
    /** 尼日利亚奈拉（ISO 4217: NGN / 566；精度 2；JDK CLDR） */
    NGN("NGN", "尼日利亚奈拉", "Nigerian Naira", "566", 2),
    /** 尼加拉瓜科多巴（ISO 4217: NIO / 558；精度 2；JDK CLDR） */
    NIO("NIO", "尼加拉瓜科多巴", "Nicaraguan Córdoba", "558", 2),
    /** 挪威克朗（ISO 4217: NOK / 578；精度 2；JDK CLDR） */
    NOK("NOK", "挪威克朗", "Norwegian Krone", "578", 2),
    /** 尼泊尔卢比（ISO 4217: NPR / 524；精度 2；JDK CLDR） */
    NPR("NPR", "尼泊尔卢比", "Nepalese Rupee", "524", 2),
    /** 新西兰元（ISO 4217: NZD / 554；精度 2；JDK CLDR） */
    NZD("NZD", "新西兰元", "New Zealand Dollar", "554", 2),
    /** 阿曼里亚尔（ISO 4217: OMR / 512；精度 3；JDK CLDR） */
    OMR("OMR", "阿曼里亚尔", "Omani Rial", "512", 3),
    /** 巴拿马巴波亚（ISO 4217: PAB / 590；精度 2；JDK CLDR） */
    PAB("PAB", "巴拿马巴波亚", "Panamanian Balboa", "590", 2),
    /** 秘鲁索尔（ISO 4217: PEN / 604；精度 2；JDK CLDR） */
    PEN("PEN", "秘鲁索尔", "Peruvian Sol", "604", 2),
    /** 巴布亚新几内亚基那（ISO 4217: PGK / 598；精度 2；JDK CLDR） */
    PGK("PGK", "巴布亚新几内亚基那", "Papua New Guinean Kina", "598", 2),
    /** 菲律宾比索（ISO 4217: PHP / 608；精度 2；JDK CLDR） */
    PHP("PHP", "菲律宾比索", "Philippine Piso", "608", 2),
    /** 巴基斯坦卢比（ISO 4217: PKR / 586；精度 2；JDK CLDR） */
    PKR("PKR", "巴基斯坦卢比", "Pakistani Rupee", "586", 2),
    /** 波兰兹罗提（ISO 4217: PLN / 985；精度 2；JDK CLDR） */
    PLN("PLN", "波兰兹罗提", "Polish Zloty", "985", 2),
    /** 巴拉圭瓜拉尼（ISO 4217: PYG / 600；精度 0；JDK CLDR） */
    PYG("PYG", "巴拉圭瓜拉尼", "Paraguayan Guarani", "600", 0),
    /** 卡塔尔里亚尔（ISO 4217: QAR / 634；精度 2；JDK CLDR） */
    QAR("QAR", "卡塔尔里亚尔", "Qatari Rial", "634", 2),
    /** 罗马尼亚列伊（ISO 4217: RON / 946；精度 2；JDK CLDR） */
    RON("RON", "罗马尼亚列伊", "Romanian Leu", "946", 2),
    /** 塞尔维亚第纳尔（ISO 4217: RSD / 941；精度 2；JDK CLDR） */
    RSD("RSD", "塞尔维亚第纳尔", "Serbian Dinar", "941", 2),
    /** 俄罗斯卢布（ISO 4217: RUB / 643；精度 2；JDK CLDR） */
    RUB("RUB", "俄罗斯卢布", "Russian Ruble", "643", 2),
    /** 卢旺达法郎（ISO 4217: RWF / 646；精度 0；JDK CLDR） */
    RWF("RWF", "卢旺达法郎", "Rwandan Franc", "646", 0),
    /** 沙特里亚尔（ISO 4217: SAR / 682；精度 2；JDK CLDR） */
    SAR("SAR", "沙特里亚尔", "Saudi Riyal", "682", 2),
    /** 所罗门群岛元（ISO 4217: SBD / 090；精度 2；JDK CLDR） */
    SBD("SBD", "所罗门群岛元", "Solomon Islands Dollar", "090", 2),
    /** 塞舌尔卢比（ISO 4217: SCR / 690；精度 2；JDK CLDR） */
    SCR("SCR", "塞舌尔卢比", "Seychellois Rupee", "690", 2),
    /** 苏丹镑（ISO 4217: SDG / 938；精度 2；JDK CLDR） */
    SDG("SDG", "苏丹镑", "Sudanese Pound", "938", 2),
    /** 瑞典克朗（ISO 4217: SEK / 752；精度 2；JDK CLDR） */
    SEK("SEK", "瑞典克朗", "Swedish Krona", "752", 2),
    /** 新加坡元（ISO 4217: SGD / 702；精度 2；JDK CLDR） */
    SGD("SGD", "新加坡元", "Singapore Dollar", "702", 2),
    /** 圣赫勒拿群岛磅（ISO 4217: SHP / 654；精度 2；JDK CLDR） */
    SHP("SHP", "圣赫勒拿群岛磅", "St. Helena Pound", "654", 2),
    /** Sierra Leonean Leone（ISO 4217: SLE / 925；精度 2；JDK CLDR） */
    SLE("SLE", "Sierra Leonean Leone", "Sierra Leonean Leone", "925", 2),
    /** 索马里先令（ISO 4217: SOS / 706；精度 2；JDK CLDR） */
    SOS("SOS", "索马里先令", "Somali Shilling", "706", 2),
    /** 苏里南元（ISO 4217: SRD / 968；精度 2；JDK CLDR） */
    SRD("SRD", "苏里南元", "Surinamese Dollar", "968", 2),
    /** 南苏丹镑（ISO 4217: SSP / 728；精度 2；JDK CLDR） */
    SSP("SSP", "南苏丹镑", "South Sudanese Pound", "728", 2),
    /** 圣多美和普林西比多布拉（ISO 4217: STN / 930；精度 2；JDK CLDR） */
    STN("STN", "圣多美和普林西比多布拉", "São Tomé & Príncipe Dobra", "930", 2),
    /** 萨尔瓦多科朗（ISO 4217: SVC / 222；精度 2；JDK CLDR） */
    SVC("SVC", "萨尔瓦多科朗", "Salvadoran Colón", "222", 2),
    /** 叙利亚镑（ISO 4217: SYP / 760；精度 2；JDK CLDR） */
    SYP("SYP", "叙利亚镑", "Syrian Pound", "760", 2),
    /** 斯威士兰里兰吉尼（ISO 4217: SZL / 748；精度 2；JDK CLDR） */
    SZL("SZL", "斯威士兰里兰吉尼", "Swazi Lilangeni", "748", 2),
    /** 泰铢（ISO 4217: THB / 764；精度 2；JDK CLDR） */
    THB("THB", "泰铢", "Thai Baht", "764", 2),
    /** 塔吉克斯坦索莫尼（ISO 4217: TJS / 972；精度 2；JDK CLDR） */
    TJS("TJS", "塔吉克斯坦索莫尼", "Tajikistani Somoni", "972", 2),
    /** 土库曼斯坦马纳特（ISO 4217: TMT / 934；精度 2；JDK CLDR） */
    TMT("TMT", "土库曼斯坦马纳特", "Turkmenistani Manat", "934", 2),
    /** 突尼斯第纳尔（ISO 4217: TND / 788；精度 3；JDK CLDR） */
    TND("TND", "突尼斯第纳尔", "Tunisian Dinar", "788", 3),
    /** 汤加潘加（ISO 4217: TOP / 776；精度 2；JDK CLDR） */
    TOP("TOP", "汤加潘加", "Tongan Paʻanga", "776", 2),
    /** 土耳其里拉（ISO 4217: TRY / 949；精度 2；JDK CLDR） */
    TRY("TRY", "土耳其里拉", "Turkish Lira", "949", 2),
    /** 特立尼达和多巴哥元（ISO 4217: TTD / 780；精度 2；JDK CLDR） */
    TTD("TTD", "特立尼达和多巴哥元", "Trinidad & Tobago Dollar", "780", 2),
    /** 新台币（ISO 4217: TWD / 901；精度 2；JDK CLDR） */
    TWD("TWD", "新台币", "New Taiwan Dollar", "901", 2),
    /** 坦桑尼亚先令（ISO 4217: TZS / 834；精度 2；JDK CLDR） */
    TZS("TZS", "坦桑尼亚先令", "Tanzanian Shilling", "834", 2),
    /** 乌克兰格里夫纳（ISO 4217: UAH / 980；精度 2；JDK CLDR） */
    UAH("UAH", "乌克兰格里夫纳", "Ukrainian Hryvnia", "980", 2),
    /** 乌干达先令（ISO 4217: UGX / 800；精度 0；JDK CLDR） */
    UGX("UGX", "乌干达先令", "Ugandan Shilling", "800", 0),
    /** 美元（ISO 4217: USD / 840；精度 2；JDK CLDR） */
    USD("USD", "美元", "US Dollar", "840", 2),
    /** 美元（次日）（ISO 4217: USN / 997；精度 2；JDK CLDR） */
    USN("USN", "美元（次日）", "US Dollar (Next day)", "997", 2),
    /** 乌拉圭比索（索引单位）（ISO 4217: UYI / 940；精度 0；JDK CLDR） */
    UYI("UYI", "乌拉圭比索（索引单位）", "Uruguayan Peso (Indexed Units)", "940", 0),
    /** 乌拉圭比索（ISO 4217: UYU / 858；精度 2；JDK CLDR） */
    UYU("UYU", "乌拉圭比索", "Uruguayan Peso", "858", 2),
    /** Unidad Previsional（ISO 4217: UYW / 927；精度 4；ISO 官方名称兜底） */
    UYW("UYW", "Unidad Previsional", "Unidad Previsional", "927", 4),
    /** 乌兹别克斯坦苏姆（ISO 4217: UZS / 860；精度 2；JDK CLDR） */
    UZS("UZS", "乌兹别克斯坦苏姆", "Uzbekistani Som", "860", 2),
    /** Venezuelan Bolívar Soberano（ISO 4217: VED / 926；精度 2；JDK CLDR） */
    VED("VED", "Venezuelan Bolívar Soberano", "Venezuelan Bolívar Soberano", "926", 2),
    /** 委内瑞拉玻利瓦尔（ISO 4217: VES / 928；精度 2；JDK CLDR） */
    VES("VES", "委内瑞拉玻利瓦尔", "Venezuelan Bolívar", "928", 2),
    /** 越南盾（ISO 4217: VND / 704；精度 0；JDK CLDR） */
    VND("VND", "越南盾", "Vietnamese Dong", "704", 0),
    /** 瓦努阿图瓦图（ISO 4217: VUV / 548；精度 0；JDK CLDR） */
    VUV("VUV", "瓦努阿图瓦图", "Vanuatu Vatu", "548", 0),
    /** 萨摩亚塔拉（ISO 4217: WST / 882；精度 2；JDK CLDR） */
    WST("WST", "萨摩亚塔拉", "Samoan Tala", "882", 2),
    /** 中非法郎（ISO 4217: XAF / 950；精度 0；JDK CLDR） */
    XAF("XAF", "中非法郎", "Central African CFA Franc", "950", 0),
    /** 银（ISO 4217: XAG / 961；精度 -1；JDK CLDR） */
    XAG("XAG", "银", "Silver", "961", -1),
    /** 黄金（ISO 4217: XAU / 959；精度 -1；JDK CLDR） */
    XAU("XAU", "黄金", "Gold", "959", -1),
    /** 欧洲复合单位（ISO 4217: XBA / 955；精度 -1；JDK CLDR） */
    XBA("XBA", "欧洲复合单位", "European Composite Unit", "955", -1),
    /** 欧洲货币联盟（ISO 4217: XBB / 956；精度 -1；JDK CLDR） */
    XBB("XBB", "欧洲货币联盟", "European Monetary Unit", "956", -1),
    /** 欧洲计算单位 (XBC)（ISO 4217: XBC / 957；精度 -1；JDK CLDR） */
    XBC("XBC", "欧洲计算单位 (XBC)", "European Unit of Account (XBC)", "957", -1),
    /** 欧洲计算单位 (XBD)（ISO 4217: XBD / 958；精度 -1；JDK CLDR） */
    XBD("XBD", "欧洲计算单位 (XBD)", "European Unit of Account (XBD)", "958", -1),
    /** 东加勒比元（ISO 4217: XCD / 951；精度 2；JDK CLDR） */
    XCD("XCD", "东加勒比元", "East Caribbean Dollar", "951", 2),
    /** 特别提款权（ISO 4217: XDR / 960；精度 -1；JDK CLDR） */
    XDR("XDR", "特别提款权", "Special Drawing Rights", "960", -1),
    /** 西非法郎（ISO 4217: XOF / 952；精度 0；JDK CLDR） */
    XOF("XOF", "西非法郎", "West African CFA Franc", "952", 0),
    /** 钯（ISO 4217: XPD / 964；精度 -1；JDK CLDR） */
    XPD("XPD", "钯", "Palladium", "964", -1),
    /** 太平洋法郎（ISO 4217: XPF / 953；精度 0；JDK CLDR） */
    XPF("XPF", "太平洋法郎", "CFP Franc", "953", 0),
    /** 铂（ISO 4217: XPT / 962；精度 -1；JDK CLDR） */
    XPT("XPT", "铂", "Platinum", "962", -1),
    /** 苏克雷（ISO 4217: XSU / 994；精度 -1；JDK CLDR） */
    XSU("XSU", "苏克雷", "Sucre", "994", -1),
    /** 测试货币代码（ISO 4217: XTS / 963；精度 -1；JDK CLDR） */
    XTS("XTS", "测试货币代码", "Testing Currency Code", "963", -1),
    /** 非洲开发银行记账单位（ISO 4217: XUA / 965；精度 -1；JDK CLDR） */
    XUA("XUA", "非洲开发银行记账单位", "ADB Unit of Account", "965", -1),
    /** 未知货币（ISO 4217: XXX / 999；精度 -1；JDK CLDR） */
    XXX("XXX", "未知货币", "Unknown Currency", "999", -1),
    /** 也门里亚尔（ISO 4217: YER / 886；精度 2；JDK CLDR） */
    YER("YER", "也门里亚尔", "Yemeni Rial", "886", 2),
    /** 南非兰特（ISO 4217: ZAR / 710；精度 2；JDK CLDR） */
    ZAR("ZAR", "南非兰特", "South African Rand", "710", 2),
    /** 赞比亚克瓦查（ISO 4217: ZMW / 967；精度 2；JDK CLDR） */
    ZMW("ZMW", "赞比亚克瓦查", "Zambian Kwacha", "967", 2),
    /** 津巴布韦金（ISO 4217: ZWG / 924；精度 2；ISO 官方名称兜底） */
    ZWG("ZWG", "津巴布韦金", "Zimbabwe Gold", "924", 2);

    /** ISO 4217 三位字母码 */
    private final String code;

    /** 中文名称 */
    private final String label;

    /** 英文名称 */
    private final String enName;

    /** ISO 4217 三位数字码 */
    private final String numericCode;

    /** 最小货币单位精度（主单位下小数位数；无小数表示的资金类代码为 -1） */
    private final int minorUnit;

    Currency(String code, String label, String enName, String numericCode, int minorUnit) {
        this.code = code;
        this.label = label;
        this.enName = enName;
        this.numericCode = numericCode;
        this.minorUnit = minorUnit;
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
    public static Currency unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code 字母码或枚举常量名（不区分大小写）
     * @return {@link Currency}
     */
    public static Currency of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：返回 {@link Optional}
     *
     * @param code 字母码或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link Currency}&gt;
     */
    public static Optional<Currency> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code 字母码或枚举常量名（不区分大小写）
     * @return {@link Currency}
     */
    public static Currency ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }

    /**
     * 严格查找：按 ISO 4217 三位数字码匹配，未匹配抛
     * {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param numericCode 数字码（如 {@code "156"}）
     * @return {@link Currency}
     */
    public static Currency ofNumericCode(String numericCode) {
        return ofNumericCodeNullable(numericCode)
                .orElseThrow(() -> new com.asialjim.standard4j.exception.BusinessException(
                        com.asialjim.standard4j.exception.SystemResCode.UNMATCHED_CODE,
                        "未知的货币数字码: [" + numericCode + "]"));
    }

    /**
     * 宽容查找：按三位数字码匹配（容忍首尾空白）
     *
     * @param numericCode 数字码
     * @return {@link Optional}&lt;{@link Currency}&gt;
     */
    public static Optional<Currency> ofNumericCodeNullable(String numericCode) {
        if (numericCode == null || numericCode.trim().isEmpty())
            return Optional.empty();
        String trimmed = numericCode.trim();
        for (Currency value : values()) {
            if (value.numericCode.equals(trimmed))
                return Optional.of(value);
        }
        return Optional.empty();
    }

    /**
     * 宽容解析：按数字码匹配，未匹配一律返回 {@link #UNKNOWN}
     *
     * @param numericCode 数字码
     * @return {@link Currency}
     */
    public static Currency ofNumericCodeOrDefault(String numericCode) {
        return ofNumericCodeNullable(numericCode).orElse(UNKNOWN);
    }
}