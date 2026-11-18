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

package com.asialjim.standard4j.region;

import com.asialjim.standard4j.code.Code;
import com.asialjim.standard4j.code.Codes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * 国家或地区 —— 依据 <b>ISO 3166-1《世界各国和地区名称代码》</b>：全量收录 249 个
 * 正式分配的 alpha-2 两字母码（含国家与特别地区，如 BV 布韦岛、HM 赫德岛），并附
 * alpha-3 三字母别名码（{@link #alpha3()}）。
 *
 * <p>中文译名采用通用简体中文译名（源自开源国家数据集），与
 * GB/T 2659《世界各国和地区及其名称代码》官方译名可能存在个别差异，发现后按官方数据校准。</p>
 *
 * <p>注：科索沃（XK）为用户自定义码，未获 ISO 正式分配，本枚举暂不收录。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CountryOrRegion implements Code {

    /** 未知/未提供（无标准码值，按本库约定取 "-"） */
    UNKNOWN("-", "未知", "Unknown", "-"),

    /** 安道尔（ISO 3166-1: AD / AND） */
    ANDORRA("AD", "安道尔", "Andorra", "AND"),
    /** 阿联酋（ISO 3166-1: AE / ARE） */
    UNITED_ARAB_EMIRATES("AE", "阿联酋", "United Arab Emirates", "ARE"),
    /** 阿富汗（ISO 3166-1: AF / AFG） */
    AFGHANISTAN("AF", "阿富汗", "Afghanistan", "AFG"),
    /** 安提瓜和巴布达（ISO 3166-1: AG / ATG） */
    ANTIGUA_AND_BARBUDA("AG", "安提瓜和巴布达", "Antigua and Barbuda", "ATG"),
    /** 安圭拉（ISO 3166-1: AI / AIA） */
    ANGUILLA("AI", "安圭拉", "Anguilla", "AIA"),
    /** 阿尔巴尼亚（ISO 3166-1: AL / ALB） */
    ALBANIA("AL", "阿尔巴尼亚", "Albania", "ALB"),
    /** 亚美尼亚（ISO 3166-1: AM / ARM） */
    ARMENIA("AM", "亚美尼亚", "Armenia", "ARM"),
    /** 安哥拉（ISO 3166-1: AO / AGO） */
    ANGOLA("AO", "安哥拉", "Angola", "AGO"),
    /** 南极洲（ISO 3166-1: AQ / ATA） */
    ANTARCTICA("AQ", "南极洲", "Antarctica", "ATA"),
    /** 阿根廷（ISO 3166-1: AR / ARG） */
    ARGENTINA("AR", "阿根廷", "Argentina", "ARG"),
    /** 美属萨摩亚（ISO 3166-1: AS / ASM） */
    AMERICAN_SAMOA("AS", "美属萨摩亚", "American Samoa", "ASM"),
    /** 奥地利（ISO 3166-1: AT / AUT） */
    AUSTRIA("AT", "奥地利", "Austria", "AUT"),
    /** 澳大利亚（ISO 3166-1: AU / AUS） */
    AUSTRALIA("AU", "澳大利亚", "Australia", "AUS"),
    /** 阿鲁巴（ISO 3166-1: AW / ABW） */
    ARUBA("AW", "阿鲁巴", "Aruba", "ABW"),
    /** 奥兰（ISO 3166-1: AX / ALA） */
    ALAND_ISLANDS("AX", "奥兰", "Åland Islands", "ALA"),
    /** 阿塞拜疆（ISO 3166-1: AZ / AZE） */
    AZERBAIJAN("AZ", "阿塞拜疆", "Azerbaijan", "AZE"),
    /** 波黑（ISO 3166-1: BA / BIH） */
    BOSNIA_AND_HERZEGOVINA("BA", "波黑", "Bosnia and Herzegovina", "BIH"),
    /** 巴巴多斯（ISO 3166-1: BB / BRB） */
    BARBADOS("BB", "巴巴多斯", "Barbados", "BRB"),
    /** 孟加拉国（ISO 3166-1: BD / BGD） */
    BANGLADESH("BD", "孟加拉国", "Bangladesh", "BGD"),
    /** 比利时（ISO 3166-1: BE / BEL） */
    BELGIUM("BE", "比利时", "Belgium", "BEL"),
    /** 布基纳法索（ISO 3166-1: BF / BFA） */
    BURKINA_FASO("BF", "布基纳法索", "Burkina Faso", "BFA"),
    /** 保加利亚（ISO 3166-1: BG / BGR） */
    BULGARIA("BG", "保加利亚", "Bulgaria", "BGR"),
    /** 巴林（ISO 3166-1: BH / BHR） */
    BAHRAIN("BH", "巴林", "Bahrain", "BHR"),
    /** 布隆迪（ISO 3166-1: BI / BDI） */
    BURUNDI("BI", "布隆迪", "Burundi", "BDI"),
    /** 贝宁（ISO 3166-1: BJ / BEN） */
    BENIN("BJ", "贝宁", "Benin", "BEN"),
    /** 圣巴泰勒米（ISO 3166-1: BL / BLM） */
    SAINT_BARTHELEMY("BL", "圣巴泰勒米", "Saint Barthélemy", "BLM"),
    /** 百慕大（ISO 3166-1: BM / BMU） */
    BERMUDA("BM", "百慕大", "Bermuda", "BMU"),
    /** 文莱（ISO 3166-1: BN / BRN） */
    BRUNEI("BN", "文莱", "Brunei", "BRN"),
    /** 玻利维亚（ISO 3166-1: BO / BOL） */
    BOLIVIA("BO", "玻利维亚", "Bolivia", "BOL"),
    /** 荷兰加勒比区（ISO 3166-1: BQ / BES） */
    CARIBBEAN_NETHERLANDS("BQ", "荷兰加勒比区", "Caribbean Netherlands", "BES"),
    /** 巴西（ISO 3166-1: BR / BRA） */
    BRAZIL("BR", "巴西", "Brazil", "BRA"),
    /** 巴哈马（ISO 3166-1: BS / BHS） */
    BAHAMAS("BS", "巴哈马", "Bahamas", "BHS"),
    /** 不丹（ISO 3166-1: BT / BTN） */
    BHUTAN("BT", "不丹", "Bhutan", "BTN"),
    /** 布韦岛（ISO 3166-1: BV / BVT） */
    BOUVET_ISLAND("BV", "布韦岛", "Bouvet Island", "BVT"),
    /** 博茨瓦纳（ISO 3166-1: BW / BWA） */
    BOTSWANA("BW", "博茨瓦纳", "Botswana", "BWA"),
    /** 白俄罗斯（ISO 3166-1: BY / BLR） */
    BELARUS("BY", "白俄罗斯", "Belarus", "BLR"),
    /** 伯利兹（ISO 3166-1: BZ / BLZ） */
    BELIZE("BZ", "伯利兹", "Belize", "BLZ"),
    /** 加拿大（ISO 3166-1: CA / CAN） */
    CANADA("CA", "加拿大", "Canada", "CAN"),
    /** 科科斯（基林）群岛（ISO 3166-1: CC / CCK） */
    COCOS_KEELING_ISLANDS("CC", "科科斯（基林）群岛", "Cocos (Keeling) Islands", "CCK"),
    /** 刚果（金）（ISO 3166-1: CD / COD） */
    DR_CONGO("CD", "刚果（金）", "DR Congo", "COD"),
    /** 中非（ISO 3166-1: CF / CAF） */
    CENTRAL_AFRICAN_REPUBLIC("CF", "中非", "Central African Republic", "CAF"),
    /** 刚果（布）（ISO 3166-1: CG / COG） */
    REPUBLIC_OF_THE_CONGO("CG", "刚果（布）", "Republic of the Congo", "COG"),
    /** 瑞士（ISO 3166-1: CH / CHE） */
    SWITZERLAND("CH", "瑞士", "Switzerland", "CHE"),
    /** 科特迪瓦（ISO 3166-1: CI / CIV） */
    IVORY_COAST("CI", "科特迪瓦", "Ivory Coast", "CIV"),
    /** 库克群岛（ISO 3166-1: CK / COK） */
    COOK_ISLANDS("CK", "库克群岛", "Cook Islands", "COK"),
    /** 智利（ISO 3166-1: CL / CHL） */
    CHILE("CL", "智利", "Chile", "CHL"),
    /** 喀麦隆（ISO 3166-1: CM / CMR） */
    CAMEROON("CM", "喀麦隆", "Cameroon", "CMR"),
    /** 中国（ISO 3166-1: CN / CHN） */
    CHINA("CN", "中国", "China", "CHN"),
    /** 哥伦比亚（ISO 3166-1: CO / COL） */
    COLOMBIA("CO", "哥伦比亚", "Colombia", "COL"),
    /** 哥斯达黎加（ISO 3166-1: CR / CRI） */
    COSTA_RICA("CR", "哥斯达黎加", "Costa Rica", "CRI"),
    /** 古巴（ISO 3166-1: CU / CUB） */
    CUBA("CU", "古巴", "Cuba", "CUB"),
    /** 佛得角（ISO 3166-1: CV / CPV） */
    CAPE_VERDE("CV", "佛得角", "Cape Verde", "CPV"),
    /** 库拉索（ISO 3166-1: CW / CUW） */
    CURACAO("CW", "库拉索", "Curaçao", "CUW"),
    /** 圣诞岛（ISO 3166-1: CX / CXR） */
    CHRISTMAS_ISLAND("CX", "圣诞岛", "Christmas Island", "CXR"),
    /** 塞浦路斯（ISO 3166-1: CY / CYP） */
    CYPRUS("CY", "塞浦路斯", "Cyprus", "CYP"),
    /** 捷克（ISO 3166-1: CZ / CZE） */
    CZECHIA("CZ", "捷克", "Czechia", "CZE"),
    /** 德国（ISO 3166-1: DE / DEU） */
    GERMANY("DE", "德国", "Germany", "DEU"),
    /** 吉布提（ISO 3166-1: DJ / DJI） */
    DJIBOUTI("DJ", "吉布提", "Djibouti", "DJI"),
    /** 丹麦（ISO 3166-1: DK / DNK） */
    DENMARK("DK", "丹麦", "Denmark", "DNK"),
    /** 多米尼克（ISO 3166-1: DM / DMA） */
    DOMINICA("DM", "多米尼克", "Dominica", "DMA"),
    /** 多米尼加（ISO 3166-1: DO / DOM） */
    DOMINICAN_REPUBLIC("DO", "多米尼加", "Dominican Republic", "DOM"),
    /** 阿尔及利亚（ISO 3166-1: DZ / DZA） */
    ALGERIA("DZ", "阿尔及利亚", "Algeria", "DZA"),
    /** 厄瓜多尔（ISO 3166-1: EC / ECU） */
    ECUADOR("EC", "厄瓜多尔", "Ecuador", "ECU"),
    /** 爱沙尼亚（ISO 3166-1: EE / EST） */
    ESTONIA("EE", "爱沙尼亚", "Estonia", "EST"),
    /** 埃及（ISO 3166-1: EG / EGY） */
    EGYPT("EG", "埃及", "Egypt", "EGY"),
    /** 阿拉伯撒哈拉民主共和国（ISO 3166-1: EH / ESH） */
    WESTERN_SAHARA("EH", "阿拉伯撒哈拉民主共和国", "Western Sahara", "ESH"),
    /** 厄立特里亚（ISO 3166-1: ER / ERI） */
    ERITREA("ER", "厄立特里亚", "Eritrea", "ERI"),
    /** 西班牙（ISO 3166-1: ES / ESP） */
    SPAIN("ES", "西班牙", "Spain", "ESP"),
    /** 埃塞俄比亚（ISO 3166-1: ET / ETH） */
    ETHIOPIA("ET", "埃塞俄比亚", "Ethiopia", "ETH"),
    /** 芬兰（ISO 3166-1: FI / FIN） */
    FINLAND("FI", "芬兰", "Finland", "FIN"),
    /** 斐济（ISO 3166-1: FJ / FJI） */
    FIJI("FJ", "斐济", "Fiji", "FJI"),
    /** 福克兰群岛（ISO 3166-1: FK / FLK） */
    FALKLAND_ISLANDS("FK", "福克兰群岛", "Falkland Islands", "FLK"),
    /** 密克罗尼西亚联邦（ISO 3166-1: FM / FSM） */
    MICRONESIA("FM", "密克罗尼西亚联邦", "Micronesia", "FSM"),
    /** 法罗群岛（ISO 3166-1: FO / FRO） */
    FAROE_ISLANDS("FO", "法罗群岛", "Faroe Islands", "FRO"),
    /** 法国（ISO 3166-1: FR / FRA） */
    FRANCE("FR", "法国", "France", "FRA"),
    /** 加蓬（ISO 3166-1: GA / GAB） */
    GABON("GA", "加蓬", "Gabon", "GAB"),
    /** 英国（ISO 3166-1: GB / GBR） */
    UNITED_KINGDOM("GB", "英国", "United Kingdom", "GBR"),
    /** 格林纳达（ISO 3166-1: GD / GRD） */
    GRENADA("GD", "格林纳达", "Grenada", "GRD"),
    /** 格鲁吉亚（ISO 3166-1: GE / GEO） */
    GEORGIA("GE", "格鲁吉亚", "Georgia", "GEO"),
    /** 法属圭亚那（ISO 3166-1: GF / GUF） */
    FRENCH_GUIANA("GF", "法属圭亚那", "French Guiana", "GUF"),
    /** 根西（ISO 3166-1: GG / GGY） */
    GUERNSEY("GG", "根西", "Guernsey", "GGY"),
    /** 加纳（ISO 3166-1: GH / GHA） */
    GHANA("GH", "加纳", "Ghana", "GHA"),
    /** 直布罗陀（ISO 3166-1: GI / GIB） */
    GIBRALTAR("GI", "直布罗陀", "Gibraltar", "GIB"),
    /** 格陵兰（ISO 3166-1: GL / GRL） */
    GREENLAND("GL", "格陵兰", "Greenland", "GRL"),
    /** 冈比亚（ISO 3166-1: GM / GMB） */
    GAMBIA("GM", "冈比亚", "Gambia", "GMB"),
    /** 几内亚（ISO 3166-1: GN / GIN） */
    GUINEA("GN", "几内亚", "Guinea", "GIN"),
    /** 瓜德罗普（ISO 3166-1: GP / GLP） */
    GUADELOUPE("GP", "瓜德罗普", "Guadeloupe", "GLP"),
    /** 赤道几内亚（ISO 3166-1: GQ / GNQ） */
    EQUATORIAL_GUINEA("GQ", "赤道几内亚", "Equatorial Guinea", "GNQ"),
    /** 希腊（ISO 3166-1: GR / GRC） */
    GREECE("GR", "希腊", "Greece", "GRC"),
    /** 南乔治亚和南桑威奇群岛（ISO 3166-1: GS / SGS） */
    SOUTH_GEORGIA("GS", "南乔治亚和南桑威奇群岛", "South Georgia", "SGS"),
    /** 危地马拉（ISO 3166-1: GT / GTM） */
    GUATEMALA("GT", "危地马拉", "Guatemala", "GTM"),
    /** 关岛（ISO 3166-1: GU / GUM） */
    GUAM("GU", "关岛", "Guam", "GUM"),
    /** 几内亚比绍（ISO 3166-1: GW / GNB） */
    GUINEA_BISSAU("GW", "几内亚比绍", "Guinea-Bissau", "GNB"),
    /** 圭亚那（ISO 3166-1: GY / GUY） */
    GUYANA("GY", "圭亚那", "Guyana", "GUY"),
    /** 中国香港（ISO 3166-1: HK / HKG） */
    HONG_KONG("HK", "中国香港", "Hong Kong", "HKG"),
    /** 赫德岛和麦克唐纳群岛（ISO 3166-1: HM / HMD） */
    HEARD_ISLAND_AND_MCDONALD_ISLANDS("HM", "赫德岛和麦克唐纳群岛", "Heard Island and McDonald Islands", "HMD"),
    /** 洪都拉斯（ISO 3166-1: HN / HND） */
    HONDURAS("HN", "洪都拉斯", "Honduras", "HND"),
    /** 克罗地亚（ISO 3166-1: HR / HRV） */
    CROATIA("HR", "克罗地亚", "Croatia", "HRV"),
    /** 海地（ISO 3166-1: HT / HTI） */
    HAITI("HT", "海地", "Haiti", "HTI"),
    /** 匈牙利（ISO 3166-1: HU / HUN） */
    HUNGARY("HU", "匈牙利", "Hungary", "HUN"),
    /** 印尼（ISO 3166-1: ID / IDN） */
    INDONESIA("ID", "印尼", "Indonesia", "IDN"),
    /** 爱尔兰（ISO 3166-1: IE / IRL） */
    IRELAND("IE", "爱尔兰", "Ireland", "IRL"),
    /** 以色列（ISO 3166-1: IL / ISR） */
    ISRAEL("IL", "以色列", "Israel", "ISR"),
    /** 马恩岛（ISO 3166-1: IM / IMN） */
    ISLE_OF_MAN("IM", "马恩岛", "Isle of Man", "IMN"),
    /** 印度（ISO 3166-1: IN / IND） */
    INDIA("IN", "印度", "India", "IND"),
    /** 英属印度洋领地（ISO 3166-1: IO / IOT） */
    BRITISH_INDIAN_OCEAN_TERRITORY("IO", "英属印度洋领地", "British Indian Ocean Territory", "IOT"),
    /** 伊拉克（ISO 3166-1: IQ / IRQ） */
    IRAQ("IQ", "伊拉克", "Iraq", "IRQ"),
    /** 伊朗（ISO 3166-1: IR / IRN） */
    IRAN("IR", "伊朗", "Iran", "IRN"),
    /** 冰岛（ISO 3166-1: IS / ISL） */
    ICELAND("IS", "冰岛", "Iceland", "ISL"),
    /** 意大利（ISO 3166-1: IT / ITA） */
    ITALY("IT", "意大利", "Italy", "ITA"),
    /** 泽西（ISO 3166-1: JE / JEY） */
    JERSEY("JE", "泽西", "Jersey", "JEY"),
    /** 牙买加（ISO 3166-1: JM / JAM） */
    JAMAICA("JM", "牙买加", "Jamaica", "JAM"),
    /** 约旦（ISO 3166-1: JO / JOR） */
    JORDAN("JO", "约旦", "Jordan", "JOR"),
    /** 日本（ISO 3166-1: JP / JPN） */
    JAPAN("JP", "日本", "Japan", "JPN"),
    /** 肯尼亚（ISO 3166-1: KE / KEN） */
    KENYA("KE", "肯尼亚", "Kenya", "KEN"),
    /** 吉尔吉斯斯坦（ISO 3166-1: KG / KGZ） */
    KYRGYZSTAN("KG", "吉尔吉斯斯坦", "Kyrgyzstan", "KGZ"),
    /** 柬埔寨（ISO 3166-1: KH / KHM） */
    CAMBODIA("KH", "柬埔寨", "Cambodia", "KHM"),
    /** 基里巴斯（ISO 3166-1: KI / KIR） */
    KIRIBATI("KI", "基里巴斯", "Kiribati", "KIR"),
    /** 科摩罗（ISO 3166-1: KM / COM） */
    COMOROS("KM", "科摩罗", "Comoros", "COM"),
    /** 圣基茨和尼维斯（ISO 3166-1: KN / KNA） */
    SAINT_KITTS_AND_NEVIS("KN", "圣基茨和尼维斯", "Saint Kitts and Nevis", "KNA"),
    /** 朝鲜（ISO 3166-1: KP / PRK） */
    NORTH_KOREA("KP", "朝鲜", "North Korea", "PRK"),
    /** 韩国（ISO 3166-1: KR / KOR） */
    SOUTH_KOREA("KR", "韩国", "South Korea", "KOR"),
    /** 科威特（ISO 3166-1: KW / KWT） */
    KUWAIT("KW", "科威特", "Kuwait", "KWT"),
    /** 开曼群岛（ISO 3166-1: KY / CYM） */
    CAYMAN_ISLANDS("KY", "开曼群岛", "Cayman Islands", "CYM"),
    /** 哈萨克斯坦（ISO 3166-1: KZ / KAZ） */
    KAZAKHSTAN("KZ", "哈萨克斯坦", "Kazakhstan", "KAZ"),
    /** 老挝（ISO 3166-1: LA / LAO） */
    LAOS("LA", "老挝", "Laos", "LAO"),
    /** 黎巴嫩（ISO 3166-1: LB / LBN） */
    LEBANON("LB", "黎巴嫩", "Lebanon", "LBN"),
    /** 圣卢西亚（ISO 3166-1: LC / LCA） */
    SAINT_LUCIA("LC", "圣卢西亚", "Saint Lucia", "LCA"),
    /** 列支敦士登（ISO 3166-1: LI / LIE） */
    LIECHTENSTEIN("LI", "列支敦士登", "Liechtenstein", "LIE"),
    /** 斯里兰卡（ISO 3166-1: LK / LKA） */
    SRI_LANKA("LK", "斯里兰卡", "Sri Lanka", "LKA"),
    /** 利比里亚（ISO 3166-1: LR / LBR） */
    LIBERIA("LR", "利比里亚", "Liberia", "LBR"),
    /** 莱索托（ISO 3166-1: LS / LSO） */
    LESOTHO("LS", "莱索托", "Lesotho", "LSO"),
    /** 立陶宛（ISO 3166-1: LT / LTU） */
    LITHUANIA("LT", "立陶宛", "Lithuania", "LTU"),
    /** 卢森堡（ISO 3166-1: LU / LUX） */
    LUXEMBOURG("LU", "卢森堡", "Luxembourg", "LUX"),
    /** 拉脱维亚（ISO 3166-1: LV / LVA） */
    LATVIA("LV", "拉脱维亚", "Latvia", "LVA"),
    /** 利比亚（ISO 3166-1: LY / LBY） */
    LIBYA("LY", "利比亚", "Libya", "LBY"),
    /** 摩洛哥（ISO 3166-1: MA / MAR） */
    MOROCCO("MA", "摩洛哥", "Morocco", "MAR"),
    /** 摩纳哥（ISO 3166-1: MC / MCO） */
    MONACO("MC", "摩纳哥", "Monaco", "MCO"),
    /** 摩尔多瓦（ISO 3166-1: MD / MDA） */
    MOLDOVA("MD", "摩尔多瓦", "Moldova", "MDA"),
    /** 黑山（ISO 3166-1: ME / MNE） */
    MONTENEGRO("ME", "黑山", "Montenegro", "MNE"),
    /** 法属圣马丁（ISO 3166-1: MF / MAF） */
    SAINT_MARTIN("MF", "法属圣马丁", "Saint Martin", "MAF"),
    /** 马达加斯加（ISO 3166-1: MG / MDG） */
    MADAGASCAR("MG", "马达加斯加", "Madagascar", "MDG"),
    /** 马绍尔群岛（ISO 3166-1: MH / MHL） */
    MARSHALL_ISLANDS("MH", "马绍尔群岛", "Marshall Islands", "MHL"),
    /** 北马其顿（ISO 3166-1: MK / MKD） */
    NORTH_MACEDONIA("MK", "北马其顿", "North Macedonia", "MKD"),
    /** 马里（ISO 3166-1: ML / MLI） */
    MALI("ML", "马里", "Mali", "MLI"),
    /** 缅甸（ISO 3166-1: MM / MMR） */
    MYANMAR("MM", "缅甸", "Myanmar", "MMR"),
    /** 蒙古（ISO 3166-1: MN / MNG） */
    MONGOLIA("MN", "蒙古", "Mongolia", "MNG"),
    /** 中国澳门（ISO 3166-1: MO / MAC） */
    MACAU("MO", "中国澳门", "Macau", "MAC"),
    /** 北马里亚纳群岛（ISO 3166-1: MP / MNP） */
    NORTHERN_MARIANA_ISLANDS("MP", "北马里亚纳群岛", "Northern Mariana Islands", "MNP"),
    /** 马提尼克（ISO 3166-1: MQ / MTQ） */
    MARTINIQUE("MQ", "马提尼克", "Martinique", "MTQ"),
    /** 毛里塔尼亚（ISO 3166-1: MR / MRT） */
    MAURITANIA("MR", "毛里塔尼亚", "Mauritania", "MRT"),
    /** 蒙特塞拉特（ISO 3166-1: MS / MSR） */
    MONTSERRAT("MS", "蒙特塞拉特", "Montserrat", "MSR"),
    /** 马耳他（ISO 3166-1: MT / MLT） */
    MALTA("MT", "马耳他", "Malta", "MLT"),
    /** 毛里求斯（ISO 3166-1: MU / MUS） */
    MAURITIUS("MU", "毛里求斯", "Mauritius", "MUS"),
    /** 马尔代夫（ISO 3166-1: MV / MDV） */
    MALDIVES("MV", "马尔代夫", "Maldives", "MDV"),
    /** 马拉维（ISO 3166-1: MW / MWI） */
    MALAWI("MW", "马拉维", "Malawi", "MWI"),
    /** 墨西哥（ISO 3166-1: MX / MEX） */
    MEXICO("MX", "墨西哥", "Mexico", "MEX"),
    /** 马来西亚（ISO 3166-1: MY / MYS） */
    MALAYSIA("MY", "马来西亚", "Malaysia", "MYS"),
    /** 莫桑比克（ISO 3166-1: MZ / MOZ） */
    MOZAMBIQUE("MZ", "莫桑比克", "Mozambique", "MOZ"),
    /** 纳米比亚（ISO 3166-1: NA / NAM） */
    NAMIBIA("NA", "纳米比亚", "Namibia", "NAM"),
    /** 新喀里多尼亚（ISO 3166-1: NC / NCL） */
    NEW_CALEDONIA("NC", "新喀里多尼亚", "New Caledonia", "NCL"),
    /** 尼日尔（ISO 3166-1: NE / NER） */
    NIGER("NE", "尼日尔", "Niger", "NER"),
    /** 诺福克岛（ISO 3166-1: NF / NFK） */
    NORFOLK_ISLAND("NF", "诺福克岛", "Norfolk Island", "NFK"),
    /** 尼日利亚（ISO 3166-1: NG / NGA） */
    NIGERIA("NG", "尼日利亚", "Nigeria", "NGA"),
    /** 尼加拉瓜（ISO 3166-1: NI / NIC） */
    NICARAGUA("NI", "尼加拉瓜", "Nicaragua", "NIC"),
    /** 荷兰（ISO 3166-1: NL / NLD） */
    NETHERLANDS("NL", "荷兰", "Netherlands", "NLD"),
    /** 挪威（ISO 3166-1: NO / NOR） */
    NORWAY("NO", "挪威", "Norway", "NOR"),
    /** 尼泊尔（ISO 3166-1: NP / NPL） */
    NEPAL("NP", "尼泊尔", "Nepal", "NPL"),
    /** 瑙鲁（ISO 3166-1: NR / NRU） */
    NAURU("NR", "瑙鲁", "Nauru", "NRU"),
    /** 纽埃（ISO 3166-1: NU / NIU） */
    NIUE("NU", "纽埃", "Niue", "NIU"),
    /** 新西兰（ISO 3166-1: NZ / NZL） */
    NEW_ZEALAND("NZ", "新西兰", "New Zealand", "NZL"),
    /** 阿曼（ISO 3166-1: OM / OMN） */
    OMAN("OM", "阿曼", "Oman", "OMN"),
    /** 巴拿马（ISO 3166-1: PA / PAN） */
    PANAMA("PA", "巴拿马", "Panama", "PAN"),
    /** 秘鲁（ISO 3166-1: PE / PER） */
    PERU("PE", "秘鲁", "Peru", "PER"),
    /** 法属波利尼西亚（ISO 3166-1: PF / PYF） */
    FRENCH_POLYNESIA("PF", "法属波利尼西亚", "French Polynesia", "PYF"),
    /** 巴布亚新几内亚（ISO 3166-1: PG / PNG） */
    PAPUA_NEW_GUINEA("PG", "巴布亚新几内亚", "Papua New Guinea", "PNG"),
    /** 菲律宾（ISO 3166-1: PH / PHL） */
    PHILIPPINES("PH", "菲律宾", "Philippines", "PHL"),
    /** 巴基斯坦（ISO 3166-1: PK / PAK） */
    PAKISTAN("PK", "巴基斯坦", "Pakistan", "PAK"),
    /** 波兰（ISO 3166-1: PL / POL） */
    POLAND("PL", "波兰", "Poland", "POL"),
    /** 圣皮埃尔和密克隆（ISO 3166-1: PM / SPM） */
    SAINT_PIERRE_AND_MIQUELON("PM", "圣皮埃尔和密克隆", "Saint Pierre and Miquelon", "SPM"),
    /** 皮特凯恩群岛（ISO 3166-1: PN / PCN） */
    PITCAIRN_ISLANDS("PN", "皮特凯恩群岛", "Pitcairn Islands", "PCN"),
    /** 波多黎各（ISO 3166-1: PR / PRI） */
    PUERTO_RICO("PR", "波多黎各", "Puerto Rico", "PRI"),
    /** 巴勒斯坦（ISO 3166-1: PS / PSE） */
    PALESTINE("PS", "巴勒斯坦", "Palestine", "PSE"),
    /** 葡萄牙（ISO 3166-1: PT / PRT） */
    PORTUGAL("PT", "葡萄牙", "Portugal", "PRT"),
    /** 帕劳（ISO 3166-1: PW / PLW） */
    PALAU("PW", "帕劳", "Palau", "PLW"),
    /** 巴拉圭（ISO 3166-1: PY / PRY） */
    PARAGUAY("PY", "巴拉圭", "Paraguay", "PRY"),
    /** 卡塔尔（ISO 3166-1: QA / QAT） */
    QATAR("QA", "卡塔尔", "Qatar", "QAT"),
    /** 留尼汪（ISO 3166-1: RE / REU） */
    REUNION("RE", "留尼汪", "Réunion", "REU"),
    /** 罗马尼亚（ISO 3166-1: RO / ROU） */
    ROMANIA("RO", "罗马尼亚", "Romania", "ROU"),
    /** 塞尔维亚（ISO 3166-1: RS / SRB） */
    SERBIA("RS", "塞尔维亚", "Serbia", "SRB"),
    /** 俄罗斯（ISO 3166-1: RU / RUS） */
    RUSSIA("RU", "俄罗斯", "Russia", "RUS"),
    /** 卢旺达（ISO 3166-1: RW / RWA） */
    RWANDA("RW", "卢旺达", "Rwanda", "RWA"),
    /** 沙特阿拉伯（ISO 3166-1: SA / SAU） */
    SAUDI_ARABIA("SA", "沙特阿拉伯", "Saudi Arabia", "SAU"),
    /** 所罗门群岛（ISO 3166-1: SB / SLB） */
    SOLOMON_ISLANDS("SB", "所罗门群岛", "Solomon Islands", "SLB"),
    /** 塞舌尔（ISO 3166-1: SC / SYC） */
    SEYCHELLES("SC", "塞舌尔", "Seychelles", "SYC"),
    /** 苏丹（ISO 3166-1: SD / SDN） */
    SUDAN("SD", "苏丹", "Sudan", "SDN"),
    /** 瑞典（ISO 3166-1: SE / SWE） */
    SWEDEN("SE", "瑞典", "Sweden", "SWE"),
    /** 新加坡（ISO 3166-1: SG / SGP） */
    SINGAPORE("SG", "新加坡", "Singapore", "SGP"),
    /** 圣赫勒拿、阿森松和特里斯坦-达库尼亚（ISO 3166-1: SH / SHN） */
    SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA("SH", "圣赫勒拿、阿森松和特里斯坦-达库尼亚", "Saint Helena, Ascension and Tristan da Cunha", "SHN"),
    /** 斯洛文尼亚（ISO 3166-1: SI / SVN） */
    SLOVENIA("SI", "斯洛文尼亚", "Slovenia", "SVN"),
    /** 斯瓦尔巴和扬马延（ISO 3166-1: SJ / SJM） */
    SVALBARD_AND_JAN_MAYEN("SJ", "斯瓦尔巴和扬马延", "Svalbard and Jan Mayen", "SJM"),
    /** 斯洛伐克（ISO 3166-1: SK / SVK） */
    SLOVAKIA("SK", "斯洛伐克", "Slovakia", "SVK"),
    /** 塞拉利昂（ISO 3166-1: SL / SLE） */
    SIERRA_LEONE("SL", "塞拉利昂", "Sierra Leone", "SLE"),
    /** 圣马力诺（ISO 3166-1: SM / SMR） */
    SAN_MARINO("SM", "圣马力诺", "San Marino", "SMR"),
    /** 塞内加尔（ISO 3166-1: SN / SEN） */
    SENEGAL("SN", "塞内加尔", "Senegal", "SEN"),
    /** 索马里（ISO 3166-1: SO / SOM） */
    SOMALIA("SO", "索马里", "Somalia", "SOM"),
    /** 苏里南（ISO 3166-1: SR / SUR） */
    SURINAME("SR", "苏里南", "Suriname", "SUR"),
    /** 南苏丹（ISO 3166-1: SS / SSD） */
    SOUTH_SUDAN("SS", "南苏丹", "South Sudan", "SSD"),
    /** 圣多美和普林西比（ISO 3166-1: ST / STP） */
    SAO_TOME_AND_PRINCIPE("ST", "圣多美和普林西比", "São Tomé and Príncipe", "STP"),
    /** 萨尔瓦多（ISO 3166-1: SV / SLV） */
    EL_SALVADOR("SV", "萨尔瓦多", "El Salvador", "SLV"),
    /** 荷属圣马丁（ISO 3166-1: SX / SXM） */
    SINT_MAARTEN("SX", "荷属圣马丁", "Sint Maarten", "SXM"),
    /** 叙利亚（ISO 3166-1: SY / SYR） */
    SYRIA("SY", "叙利亚", "Syria", "SYR"),
    /** 斯威士兰（ISO 3166-1: SZ / SWZ） */
    ESWATINI("SZ", "斯威士兰", "Eswatini", "SWZ"),
    /** 特克斯和凯科斯群岛（ISO 3166-1: TC / TCA） */
    TURKS_AND_CAICOS_ISLANDS("TC", "特克斯和凯科斯群岛", "Turks and Caicos Islands", "TCA"),
    /** 乍得（ISO 3166-1: TD / TCD） */
    CHAD("TD", "乍得", "Chad", "TCD"),
    /** 法属南部领地（ISO 3166-1: TF / ATF） */
    FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS("TF", "法属南部领地", "French Southern and Antarctic Lands", "ATF"),
    /** 多哥（ISO 3166-1: TG / TGO） */
    TOGO("TG", "多哥", "Togo", "TGO"),
    /** 泰国（ISO 3166-1: TH / THA） */
    THAILAND("TH", "泰国", "Thailand", "THA"),
    /** 塔吉克斯坦（ISO 3166-1: TJ / TJK） */
    TAJIKISTAN("TJ", "塔吉克斯坦", "Tajikistan", "TJK"),
    /** 托克劳（ISO 3166-1: TK / TKL） */
    TOKELAU("TK", "托克劳", "Tokelau", "TKL"),
    /** 东帝汶（ISO 3166-1: TL / TLS） */
    TIMOR_LESTE("TL", "东帝汶", "Timor-Leste", "TLS"),
    /** 土库曼斯坦（ISO 3166-1: TM / TKM） */
    TURKMENISTAN("TM", "土库曼斯坦", "Turkmenistan", "TKM"),
    /** 突尼斯（ISO 3166-1: TN / TUN） */
    TUNISIA("TN", "突尼斯", "Tunisia", "TUN"),
    /** 汤加（ISO 3166-1: TO / TON） */
    TONGA("TO", "汤加", "Tonga", "TON"),
    /** 土耳其（ISO 3166-1: TR / TUR） */
    TURKIYE("TR", "土耳其", "Türkiye", "TUR"),
    /** 特立尼达和多巴哥（ISO 3166-1: TT / TTO） */
    TRINIDAD_AND_TOBAGO("TT", "特立尼达和多巴哥", "Trinidad and Tobago", "TTO"),
    /** 图瓦卢（ISO 3166-1: TV / TUV） */
    TUVALU("TV", "图瓦卢", "Tuvalu", "TUV"),
    /** 中国台湾省（ISO 3166-1: TW / TWN） */
    TAIWAN("TW", "中国台湾省", "Taiwan", "TWN"),
    /** 坦桑尼亚（ISO 3166-1: TZ / TZA） */
    TANZANIA("TZ", "坦桑尼亚", "Tanzania", "TZA"),
    /** 乌克兰（ISO 3166-1: UA / UKR） */
    UKRAINE("UA", "乌克兰", "Ukraine", "UKR"),
    /** 乌干达（ISO 3166-1: UG / UGA） */
    UGANDA("UG", "乌干达", "Uganda", "UGA"),
    /** 美国本土外小岛屿（ISO 3166-1: UM / UMI） */
    UNITED_STATES_MINOR_OUTLYING_ISLANDS("UM", "美国本土外小岛屿", "United States Minor Outlying Islands", "UMI"),
    /** 美国（ISO 3166-1: US / USA） */
    UNITED_STATES("US", "美国", "United States", "USA"),
    /** 乌拉圭（ISO 3166-1: UY / URY） */
    URUGUAY("UY", "乌拉圭", "Uruguay", "URY"),
    /** 乌兹别克斯坦（ISO 3166-1: UZ / UZB） */
    UZBEKISTAN("UZ", "乌兹别克斯坦", "Uzbekistan", "UZB"),
    /** 梵蒂冈（ISO 3166-1: VA / VAT） */
    VATICAN_CITY("VA", "梵蒂冈", "Vatican City", "VAT"),
    /** 圣文森特和格林纳丁斯（ISO 3166-1: VC / VCT） */
    SAINT_VINCENT_AND_THE_GRENADINES("VC", "圣文森特和格林纳丁斯", "Saint Vincent and the Grenadines", "VCT"),
    /** 委内瑞拉（ISO 3166-1: VE / VEN） */
    VENEZUELA("VE", "委内瑞拉", "Venezuela", "VEN"),
    /** 英属维尔京群岛（ISO 3166-1: VG / VGB） */
    BRITISH_VIRGIN_ISLANDS("VG", "英属维尔京群岛", "British Virgin Islands", "VGB"),
    /** 美属维尔京群岛（ISO 3166-1: VI / VIR） */
    UNITED_STATES_VIRGIN_ISLANDS("VI", "美属维尔京群岛", "United States Virgin Islands", "VIR"),
    /** 越南（ISO 3166-1: VN / VNM） */
    VIETNAM("VN", "越南", "Vietnam", "VNM"),
    /** 瓦努阿图（ISO 3166-1: VU / VUT） */
    VANUATU("VU", "瓦努阿图", "Vanuatu", "VUT"),
    /** 瓦利斯和富图纳（ISO 3166-1: WF / WLF） */
    WALLIS_AND_FUTUNA("WF", "瓦利斯和富图纳", "Wallis and Futuna", "WLF"),
    /** 萨摩亚（ISO 3166-1: WS / WSM） */
    SAMOA("WS", "萨摩亚", "Samoa", "WSM"),
    /** 也门（ISO 3166-1: YE / YEM） */
    YEMEN("YE", "也门", "Yemen", "YEM"),
    /** 马约特（ISO 3166-1: YT / MYT） */
    MAYOTTE("YT", "马约特", "Mayotte", "MYT"),
    /** 南非（ISO 3166-1: ZA / ZAF） */
    SOUTH_AFRICA("ZA", "南非", "South Africa", "ZAF"),
    /** 赞比亚（ISO 3166-1: ZM / ZMB） */
    ZAMBIA("ZM", "赞比亚", "Zambia", "ZMB"),
    /** 津巴布韦（ISO 3166-1: ZW / ZWE） */
    ZIMBABWE("ZW", "津巴布韦", "Zimbabwe", "ZWE");

    /** ISO 3166-1 alpha-2 主码 */
    private final String code;

    /** 中文名称 */
    private final String label;

    /** 英文名称 */
    private final String enName;

    /** ISO 3166-1 alpha-3 别名码 */
    private final String alpha3;

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
    public static CountryOrRegion unknown() {
        return UNKNOWN;
    }

    /**
     * 严格查找：按 alpha-2 码值或枚举常量名匹配，未匹配抛
     * {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param code alpha-2 码值或枚举常量名（不区分大小写）
     * @return {@link CountryOrRegion}
     */
    public static CountryOrRegion of(String code) {
        return Codes.of(values(), code);
    }

    /**
     * 宽容查找：按 alpha-2 码值或枚举常量名匹配
     *
     * @param code alpha-2 码值或枚举常量名（不区分大小写）
     * @return {@link Optional}&lt;{@link CountryOrRegion}&gt;
     */
    public static Optional<CountryOrRegion> ofNullable(String code) {
        return Codes.ofNullable(values(), code);
    }

    /**
     * 宽容解析：未匹配/无效入参一律返回 {@link #UNKNOWN}
     *
     * @param code alpha-2 码值或枚举常量名（不区分大小写）
     * @return {@link CountryOrRegion}
     */
    public static CountryOrRegion ofOrDefault(String code) {
        return Codes.ofOrDefault(values(), code, UNKNOWN);
    }

    /**
     * 严格查找：按 alpha-3 别名码匹配，未匹配抛
     * {@link com.asialjim.standard4j.exception.BusinessException}
     *
     * @param alpha3 alpha-3 码值（不区分大小写）
     * @return {@link CountryOrRegion}
     */
    public static CountryOrRegion ofAlpha3(String alpha3) {
        return ofAlpha3Nullable(alpha3)
                .orElseThrow(() -> new com.asialjim.standard4j.exception.BusinessException(
                        com.asialjim.standard4j.exception.SystemResCode.UNMATCHED_CODE,
                        "未知的国家或地区 alpha-3 码: [" + alpha3 + "]"));
    }

    /**
     * 宽容查找：按 alpha-3 别名码匹配（不区分大小写，容忍首尾空白）
     *
     * @param alpha3 alpha-3 码值
     * @return {@link Optional}&lt;{@link CountryOrRegion}&gt;
     */
    public static Optional<CountryOrRegion> ofAlpha3Nullable(String alpha3) {
        if (alpha3 == null || alpha3.trim().isEmpty())
            return Optional.empty();
        String trimmed = alpha3.trim();
        for (CountryOrRegion value : values()) {
            if (value.alpha3.equalsIgnoreCase(trimmed))
                return Optional.of(value);
        }
        return Optional.empty();
    }

    /**
     * 宽容解析：按 alpha-3 别名码匹配，未匹配一律返回 {@link #UNKNOWN}
     *
     * @param alpha3 alpha-3 码值
     * @return {@link CountryOrRegion}
     */
    public static CountryOrRegion ofAlpha3OrDefault(String alpha3) {
        return ofAlpha3Nullable(alpha3).orElse(UNKNOWN);
    }
}