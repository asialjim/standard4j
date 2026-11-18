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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 国家或地区 —— 依据 <b>ISO 3166-1《世界各国和地区名称代码》</b>：全量收录 249 个
 * 正式分配的 alpha-2 两字母码（含国家与特别地区，如 BV 布韦岛、HM 赫德岛），并附
 * alpha-3 三字母别名码（{@link #alpha3()}）与电信区号（{@link #idd()}，ITU-T E.164 国家/地区码）。
 *
 * <p>中文译名采用通用简体中文译名（CLDR-zh，源自开源国家数据集），与
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
    UNKNOWN("-", "未知", "Unknown", "-", "-"),

        /** 安道尔（ISO 3166-1: AD / AND；电信区号 376） */
    ANDORRA("AD", "安道尔", "Andorra", "AND", "376"),
    /** 阿联酋（ISO 3166-1: AE / ARE；电信区号 971） */
    UNITED_ARAB_EMIRATES("AE", "阿联酋", "United Arab Emirates", "ARE", "971"),
    /** 阿富汗（ISO 3166-1: AF / AFG；电信区号 93） */
    AFGHANISTAN("AF", "阿富汗", "Afghanistan", "AFG", "93"),
    /** 安提瓜和巴布达（ISO 3166-1: AG / ATG；电信区号 1268） */
    ANTIGUA_AND_BARBUDA("AG", "安提瓜和巴布达", "Antigua and Barbuda", "ATG", "1268"),
    /** 安圭拉（ISO 3166-1: AI / AIA；电信区号 1264） */
    ANGUILLA("AI", "安圭拉", "Anguilla", "AIA", "1264"),
    /** 阿尔巴尼亚（ISO 3166-1: AL / ALB；电信区号 355） */
    ALBANIA("AL", "阿尔巴尼亚", "Albania", "ALB", "355"),
    /** 亚美尼亚（ISO 3166-1: AM / ARM；电信区号 374） */
    ARMENIA("AM", "亚美尼亚", "Armenia", "ARM", "374"),
    /** 安哥拉（ISO 3166-1: AO / AGO；电信区号 244） */
    ANGOLA("AO", "安哥拉", "Angola", "AGO", "244"),
    /** 南极洲（ISO 3166-1: AQ / ATA；无电信区号） */
    ANTARCTICA("AQ", "南极洲", "Antarctica", "ATA", ""),
    /** 阿根廷（ISO 3166-1: AR / ARG；电信区号 54） */
    ARGENTINA("AR", "阿根廷", "Argentina", "ARG", "54"),
    /** 美属萨摩亚（ISO 3166-1: AS / ASM；电信区号 1684） */
    AMERICAN_SAMOA("AS", "美属萨摩亚", "American Samoa", "ASM", "1684"),
    /** 奥地利（ISO 3166-1: AT / AUT；电信区号 43） */
    AUSTRIA("AT", "奥地利", "Austria", "AUT", "43"),
    /** 澳大利亚（ISO 3166-1: AU / AUS；电信区号 61） */
    AUSTRALIA("AU", "澳大利亚", "Australia", "AUS", "61"),
    /** 阿鲁巴（ISO 3166-1: AW / ABW；电信区号 297） */
    ARUBA("AW", "阿鲁巴", "Aruba", "ABW", "297"),
    /** 奥兰（ISO 3166-1: AX / ALA；电信区号 35818） */
    ALAND_ISLANDS("AX", "奥兰", "Åland Islands", "ALA", "35818"),
    /** 阿塞拜疆（ISO 3166-1: AZ / AZE；电信区号 994） */
    AZERBAIJAN("AZ", "阿塞拜疆", "Azerbaijan", "AZE", "994"),
    /** 波黑（ISO 3166-1: BA / BIH；电信区号 387） */
    BOSNIA_AND_HERZEGOVINA("BA", "波黑", "Bosnia and Herzegovina", "BIH", "387"),
    /** 巴巴多斯（ISO 3166-1: BB / BRB；电信区号 1246） */
    BARBADOS("BB", "巴巴多斯", "Barbados", "BRB", "1246"),
    /** 孟加拉国（ISO 3166-1: BD / BGD；电信区号 880） */
    BANGLADESH("BD", "孟加拉国", "Bangladesh", "BGD", "880"),
    /** 比利时（ISO 3166-1: BE / BEL；电信区号 32） */
    BELGIUM("BE", "比利时", "Belgium", "BEL", "32"),
    /** 布基纳法索（ISO 3166-1: BF / BFA；电信区号 226） */
    BURKINA_FASO("BF", "布基纳法索", "Burkina Faso", "BFA", "226"),
    /** 保加利亚（ISO 3166-1: BG / BGR；电信区号 359） */
    BULGARIA("BG", "保加利亚", "Bulgaria", "BGR", "359"),
    /** 巴林（ISO 3166-1: BH / BHR；电信区号 973） */
    BAHRAIN("BH", "巴林", "Bahrain", "BHR", "973"),
    /** 布隆迪（ISO 3166-1: BI / BDI；电信区号 257） */
    BURUNDI("BI", "布隆迪", "Burundi", "BDI", "257"),
    /** 贝宁（ISO 3166-1: BJ / BEN；电信区号 229） */
    BENIN("BJ", "贝宁", "Benin", "BEN", "229"),
    /** 圣巴泰勒米（ISO 3166-1: BL / BLM；电信区号 590） */
    SAINT_BARTHELEMY("BL", "圣巴泰勒米", "Saint Barthélemy", "BLM", "590"),
    /** 百慕大（ISO 3166-1: BM / BMU；电信区号 1441） */
    BERMUDA("BM", "百慕大", "Bermuda", "BMU", "1441"),
    /** 文莱（ISO 3166-1: BN / BRN；电信区号 673） */
    BRUNEI("BN", "文莱", "Brunei", "BRN", "673"),
    /** 玻利维亚（ISO 3166-1: BO / BOL；电信区号 591） */
    BOLIVIA("BO", "玻利维亚", "Bolivia", "BOL", "591"),
    /** 荷兰加勒比区（ISO 3166-1: BQ / BES；电信区号 599） */
    CARIBBEAN_NETHERLANDS("BQ", "荷兰加勒比区", "Caribbean Netherlands", "BES", "599"),
    /** 巴西（ISO 3166-1: BR / BRA；电信区号 55） */
    BRAZIL("BR", "巴西", "Brazil", "BRA", "55"),
    /** 巴哈马（ISO 3166-1: BS / BHS；电信区号 1242） */
    BAHAMAS("BS", "巴哈马", "Bahamas", "BHS", "1242"),
    /** 不丹（ISO 3166-1: BT / BTN；电信区号 975） */
    BHUTAN("BT", "不丹", "Bhutan", "BTN", "975"),
    /** 布韦岛（ISO 3166-1: BV / BVT；电信区号 47） */
    BOUVET_ISLAND("BV", "布韦岛", "Bouvet Island", "BVT", "47"),
    /** 博茨瓦纳（ISO 3166-1: BW / BWA；电信区号 267） */
    BOTSWANA("BW", "博茨瓦纳", "Botswana", "BWA", "267"),
    /** 白俄罗斯（ISO 3166-1: BY / BLR；电信区号 375） */
    BELARUS("BY", "白俄罗斯", "Belarus", "BLR", "375"),
    /** 伯利兹（ISO 3166-1: BZ / BLZ；电信区号 501） */
    BELIZE("BZ", "伯利兹", "Belize", "BLZ", "501"),
    /** 加拿大（ISO 3166-1: CA / CAN；电信区号 1） */
    CANADA("CA", "加拿大", "Canada", "CAN", "1"),
    /** 科科斯（基林）群岛（ISO 3166-1: CC / CCK；电信区号 61） */
    COCOS_KEELING_ISLANDS("CC", "科科斯（基林）群岛", "Cocos (Keeling) Islands", "CCK", "61"),
    /** 刚果（金）（ISO 3166-1: CD / COD；电信区号 243） */
    DR_CONGO("CD", "刚果（金）", "DR Congo", "COD", "243"),
    /** 中非（ISO 3166-1: CF / CAF；电信区号 236） */
    CENTRAL_AFRICAN_REPUBLIC("CF", "中非", "Central African Republic", "CAF", "236"),
    /** 刚果（布）（ISO 3166-1: CG / COG；电信区号 242） */
    REPUBLIC_OF_THE_CONGO("CG", "刚果（布）", "Republic of the Congo", "COG", "242"),
    /** 瑞士（ISO 3166-1: CH / CHE；电信区号 41） */
    SWITZERLAND("CH", "瑞士", "Switzerland", "CHE", "41"),
    /** 科特迪瓦（ISO 3166-1: CI / CIV；电信区号 225） */
    IVORY_COAST("CI", "科特迪瓦", "Ivory Coast", "CIV", "225"),
    /** 库克群岛（ISO 3166-1: CK / COK；电信区号 682） */
    COOK_ISLANDS("CK", "库克群岛", "Cook Islands", "COK", "682"),
    /** 智利（ISO 3166-1: CL / CHL；电信区号 56） */
    CHILE("CL", "智利", "Chile", "CHL", "56"),
    /** 喀麦隆（ISO 3166-1: CM / CMR；电信区号 237） */
    CAMEROON("CM", "喀麦隆", "Cameroon", "CMR", "237"),
    /** 中国（ISO 3166-1: CN / CHN；电信区号 86） */
    CHINA("CN", "中国", "China", "CHN", "86"),
    /** 哥伦比亚（ISO 3166-1: CO / COL；电信区号 57） */
    COLOMBIA("CO", "哥伦比亚", "Colombia", "COL", "57"),
    /** 哥斯达黎加（ISO 3166-1: CR / CRI；电信区号 506） */
    COSTA_RICA("CR", "哥斯达黎加", "Costa Rica", "CRI", "506"),
    /** 古巴（ISO 3166-1: CU / CUB；电信区号 53） */
    CUBA("CU", "古巴", "Cuba", "CUB", "53"),
    /** 佛得角（ISO 3166-1: CV / CPV；电信区号 238） */
    CAPE_VERDE("CV", "佛得角", "Cape Verde", "CPV", "238"),
    /** 库拉索（ISO 3166-1: CW / CUW；电信区号 599） */
    CURACAO("CW", "库拉索", "Curaçao", "CUW", "599"),
    /** 圣诞岛（ISO 3166-1: CX / CXR；电信区号 61） */
    CHRISTMAS_ISLAND("CX", "圣诞岛", "Christmas Island", "CXR", "61"),
    /** 塞浦路斯（ISO 3166-1: CY / CYP；电信区号 357） */
    CYPRUS("CY", "塞浦路斯", "Cyprus", "CYP", "357"),
    /** 捷克（ISO 3166-1: CZ / CZE；电信区号 420） */
    CZECHIA("CZ", "捷克", "Czechia", "CZE", "420"),
    /** 德国（ISO 3166-1: DE / DEU；电信区号 49） */
    GERMANY("DE", "德国", "Germany", "DEU", "49"),
    /** 吉布提（ISO 3166-1: DJ / DJI；电信区号 253） */
    DJIBOUTI("DJ", "吉布提", "Djibouti", "DJI", "253"),
    /** 丹麦（ISO 3166-1: DK / DNK；电信区号 45） */
    DENMARK("DK", "丹麦", "Denmark", "DNK", "45"),
    /** 多米尼克（ISO 3166-1: DM / DMA；电信区号 1767） */
    DOMINICA("DM", "多米尼克", "Dominica", "DMA", "1767"),
    /** 多米尼加（ISO 3166-1: DO / DOM；电信区号 1809） */
    DOMINICAN_REPUBLIC("DO", "多米尼加", "Dominican Republic", "DOM", "1809"),
    /** 阿尔及利亚（ISO 3166-1: DZ / DZA；电信区号 213） */
    ALGERIA("DZ", "阿尔及利亚", "Algeria", "DZA", "213"),
    /** 厄瓜多尔（ISO 3166-1: EC / ECU；电信区号 593） */
    ECUADOR("EC", "厄瓜多尔", "Ecuador", "ECU", "593"),
    /** 爱沙尼亚（ISO 3166-1: EE / EST；电信区号 372） */
    ESTONIA("EE", "爱沙尼亚", "Estonia", "EST", "372"),
    /** 埃及（ISO 3166-1: EG / EGY；电信区号 20） */
    EGYPT("EG", "埃及", "Egypt", "EGY", "20"),
    /** 阿拉伯撒哈拉民主共和国（ISO 3166-1: EH / ESH；电信区号 2125288） */
    WESTERN_SAHARA("EH", "阿拉伯撒哈拉民主共和国", "Western Sahara", "ESH", "2125288"),
    /** 厄立特里亚（ISO 3166-1: ER / ERI；电信区号 291） */
    ERITREA("ER", "厄立特里亚", "Eritrea", "ERI", "291"),
    /** 西班牙（ISO 3166-1: ES / ESP；电信区号 34） */
    SPAIN("ES", "西班牙", "Spain", "ESP", "34"),
    /** 埃塞俄比亚（ISO 3166-1: ET / ETH；电信区号 251） */
    ETHIOPIA("ET", "埃塞俄比亚", "Ethiopia", "ETH", "251"),
    /** 芬兰（ISO 3166-1: FI / FIN；电信区号 358） */
    FINLAND("FI", "芬兰", "Finland", "FIN", "358"),
    /** 斐济（ISO 3166-1: FJ / FJI；电信区号 679） */
    FIJI("FJ", "斐济", "Fiji", "FJI", "679"),
    /** 福克兰群岛（ISO 3166-1: FK / FLK；电信区号 500） */
    FALKLAND_ISLANDS("FK", "福克兰群岛", "Falkland Islands", "FLK", "500"),
    /** 密克罗尼西亚联邦（ISO 3166-1: FM / FSM；电信区号 691） */
    MICRONESIA("FM", "密克罗尼西亚联邦", "Micronesia", "FSM", "691"),
    /** 法罗群岛（ISO 3166-1: FO / FRO；电信区号 298） */
    FAROE_ISLANDS("FO", "法罗群岛", "Faroe Islands", "FRO", "298"),
    /** 法国（ISO 3166-1: FR / FRA；电信区号 33） */
    FRANCE("FR", "法国", "France", "FRA", "33"),
    /** 加蓬（ISO 3166-1: GA / GAB；电信区号 241） */
    GABON("GA", "加蓬", "Gabon", "GAB", "241"),
    /** 英国（ISO 3166-1: GB / GBR；电信区号 44） */
    UNITED_KINGDOM("GB", "英国", "United Kingdom", "GBR", "44"),
    /** 格林纳达（ISO 3166-1: GD / GRD；电信区号 1473） */
    GRENADA("GD", "格林纳达", "Grenada", "GRD", "1473"),
    /** 格鲁吉亚（ISO 3166-1: GE / GEO；电信区号 995） */
    GEORGIA("GE", "格鲁吉亚", "Georgia", "GEO", "995"),
    /** 法属圭亚那（ISO 3166-1: GF / GUF；电信区号 594） */
    FRENCH_GUIANA("GF", "法属圭亚那", "French Guiana", "GUF", "594"),
    /** 根西（ISO 3166-1: GG / GGY；电信区号 44） */
    GUERNSEY("GG", "根西", "Guernsey", "GGY", "44"),
    /** 加纳（ISO 3166-1: GH / GHA；电信区号 233） */
    GHANA("GH", "加纳", "Ghana", "GHA", "233"),
    /** 直布罗陀（ISO 3166-1: GI / GIB；电信区号 350） */
    GIBRALTAR("GI", "直布罗陀", "Gibraltar", "GIB", "350"),
    /** 格陵兰（ISO 3166-1: GL / GRL；电信区号 299） */
    GREENLAND("GL", "格陵兰", "Greenland", "GRL", "299"),
    /** 冈比亚（ISO 3166-1: GM / GMB；电信区号 220） */
    GAMBIA("GM", "冈比亚", "Gambia", "GMB", "220"),
    /** 几内亚（ISO 3166-1: GN / GIN；电信区号 224） */
    GUINEA("GN", "几内亚", "Guinea", "GIN", "224"),
    /** 瓜德罗普（ISO 3166-1: GP / GLP；电信区号 590） */
    GUADELOUPE("GP", "瓜德罗普", "Guadeloupe", "GLP", "590"),
    /** 赤道几内亚（ISO 3166-1: GQ / GNQ；电信区号 240） */
    EQUATORIAL_GUINEA("GQ", "赤道几内亚", "Equatorial Guinea", "GNQ", "240"),
    /** 希腊（ISO 3166-1: GR / GRC；电信区号 30） */
    GREECE("GR", "希腊", "Greece", "GRC", "30"),
    /** 南乔治亚和南桑威奇群岛（ISO 3166-1: GS / SGS；电信区号 500） */
    SOUTH_GEORGIA("GS", "南乔治亚和南桑威奇群岛", "South Georgia", "SGS", "500"),
    /** 危地马拉（ISO 3166-1: GT / GTM；电信区号 502） */
    GUATEMALA("GT", "危地马拉", "Guatemala", "GTM", "502"),
    /** 关岛（ISO 3166-1: GU / GUM；电信区号 1671） */
    GUAM("GU", "关岛", "Guam", "GUM", "1671"),
    /** 几内亚比绍（ISO 3166-1: GW / GNB；电信区号 245） */
    GUINEA_BISSAU("GW", "几内亚比绍", "Guinea-Bissau", "GNB", "245"),
    /** 圭亚那（ISO 3166-1: GY / GUY；电信区号 592） */
    GUYANA("GY", "圭亚那", "Guyana", "GUY", "592"),
    /** 中国香港（ISO 3166-1: HK / HKG；电信区号 852） */
    HONG_KONG("HK", "中国香港", "Hong Kong", "HKG", "852"),
    /** 赫德岛和麦克唐纳群岛（ISO 3166-1: HM / HMD；无电信区号） */
    HEARD_ISLAND_AND_MCDONALD_ISLANDS("HM", "赫德岛和麦克唐纳群岛", "Heard Island and McDonald Islands", "HMD", ""),
    /** 洪都拉斯（ISO 3166-1: HN / HND；电信区号 504） */
    HONDURAS("HN", "洪都拉斯", "Honduras", "HND", "504"),
    /** 克罗地亚（ISO 3166-1: HR / HRV；电信区号 385） */
    CROATIA("HR", "克罗地亚", "Croatia", "HRV", "385"),
    /** 海地（ISO 3166-1: HT / HTI；电信区号 509） */
    HAITI("HT", "海地", "Haiti", "HTI", "509"),
    /** 匈牙利（ISO 3166-1: HU / HUN；电信区号 36） */
    HUNGARY("HU", "匈牙利", "Hungary", "HUN", "36"),
    /** 印尼（ISO 3166-1: ID / IDN；电信区号 62） */
    INDONESIA("ID", "印尼", "Indonesia", "IDN", "62"),
    /** 爱尔兰（ISO 3166-1: IE / IRL；电信区号 353） */
    IRELAND("IE", "爱尔兰", "Ireland", "IRL", "353"),
    /** 以色列（ISO 3166-1: IL / ISR；电信区号 972） */
    ISRAEL("IL", "以色列", "Israel", "ISR", "972"),
    /** 马恩岛（ISO 3166-1: IM / IMN；电信区号 44） */
    ISLE_OF_MAN("IM", "马恩岛", "Isle of Man", "IMN", "44"),
    /** 印度（ISO 3166-1: IN / IND；电信区号 91） */
    INDIA("IN", "印度", "India", "IND", "91"),
    /** 英属印度洋领地（ISO 3166-1: IO / IOT；电信区号 246） */
    BRITISH_INDIAN_OCEAN_TERRITORY("IO", "英属印度洋领地", "British Indian Ocean Territory", "IOT", "246"),
    /** 伊拉克（ISO 3166-1: IQ / IRQ；电信区号 964） */
    IRAQ("IQ", "伊拉克", "Iraq", "IRQ", "964"),
    /** 伊朗（ISO 3166-1: IR / IRN；电信区号 98） */
    IRAN("IR", "伊朗", "Iran", "IRN", "98"),
    /** 冰岛（ISO 3166-1: IS / ISL；电信区号 354） */
    ICELAND("IS", "冰岛", "Iceland", "ISL", "354"),
    /** 意大利（ISO 3166-1: IT / ITA；电信区号 39） */
    ITALY("IT", "意大利", "Italy", "ITA", "39"),
    /** 泽西（ISO 3166-1: JE / JEY；电信区号 44） */
    JERSEY("JE", "泽西", "Jersey", "JEY", "44"),
    /** 牙买加（ISO 3166-1: JM / JAM；电信区号 1876） */
    JAMAICA("JM", "牙买加", "Jamaica", "JAM", "1876"),
    /** 约旦（ISO 3166-1: JO / JOR；电信区号 962） */
    JORDAN("JO", "约旦", "Jordan", "JOR", "962"),
    /** 日本（ISO 3166-1: JP / JPN；电信区号 81） */
    JAPAN("JP", "日本", "Japan", "JPN", "81"),
    /** 肯尼亚（ISO 3166-1: KE / KEN；电信区号 254） */
    KENYA("KE", "肯尼亚", "Kenya", "KEN", "254"),
    /** 吉尔吉斯斯坦（ISO 3166-1: KG / KGZ；电信区号 996） */
    KYRGYZSTAN("KG", "吉尔吉斯斯坦", "Kyrgyzstan", "KGZ", "996"),
    /** 柬埔寨（ISO 3166-1: KH / KHM；电信区号 855） */
    CAMBODIA("KH", "柬埔寨", "Cambodia", "KHM", "855"),
    /** 基里巴斯（ISO 3166-1: KI / KIR；电信区号 686） */
    KIRIBATI("KI", "基里巴斯", "Kiribati", "KIR", "686"),
    /** 科摩罗（ISO 3166-1: KM / COM；电信区号 269） */
    COMOROS("KM", "科摩罗", "Comoros", "COM", "269"),
    /** 圣基茨和尼维斯（ISO 3166-1: KN / KNA；电信区号 1869） */
    SAINT_KITTS_AND_NEVIS("KN", "圣基茨和尼维斯", "Saint Kitts and Nevis", "KNA", "1869"),
    /** 朝鲜（ISO 3166-1: KP / PRK；电信区号 850） */
    NORTH_KOREA("KP", "朝鲜", "North Korea", "PRK", "850"),
    /** 韩国（ISO 3166-1: KR / KOR；电信区号 82） */
    SOUTH_KOREA("KR", "韩国", "South Korea", "KOR", "82"),
    /** 科威特（ISO 3166-1: KW / KWT；电信区号 965） */
    KUWAIT("KW", "科威特", "Kuwait", "KWT", "965"),
    /** 开曼群岛（ISO 3166-1: KY / CYM；电信区号 1345） */
    CAYMAN_ISLANDS("KY", "开曼群岛", "Cayman Islands", "CYM", "1345"),
    /** 哈萨克斯坦（ISO 3166-1: KZ / KAZ；电信区号 7） */
    KAZAKHSTAN("KZ", "哈萨克斯坦", "Kazakhstan", "KAZ", "7"),
    /** 老挝（ISO 3166-1: LA / LAO；电信区号 856） */
    LAOS("LA", "老挝", "Laos", "LAO", "856"),
    /** 黎巴嫩（ISO 3166-1: LB / LBN；电信区号 961） */
    LEBANON("LB", "黎巴嫩", "Lebanon", "LBN", "961"),
    /** 圣卢西亚（ISO 3166-1: LC / LCA；电信区号 1758） */
    SAINT_LUCIA("LC", "圣卢西亚", "Saint Lucia", "LCA", "1758"),
    /** 列支敦士登（ISO 3166-1: LI / LIE；电信区号 423） */
    LIECHTENSTEIN("LI", "列支敦士登", "Liechtenstein", "LIE", "423"),
    /** 斯里兰卡（ISO 3166-1: LK / LKA；电信区号 94） */
    SRI_LANKA("LK", "斯里兰卡", "Sri Lanka", "LKA", "94"),
    /** 利比里亚（ISO 3166-1: LR / LBR；电信区号 231） */
    LIBERIA("LR", "利比里亚", "Liberia", "LBR", "231"),
    /** 莱索托（ISO 3166-1: LS / LSO；电信区号 266） */
    LESOTHO("LS", "莱索托", "Lesotho", "LSO", "266"),
    /** 立陶宛（ISO 3166-1: LT / LTU；电信区号 370） */
    LITHUANIA("LT", "立陶宛", "Lithuania", "LTU", "370"),
    /** 卢森堡（ISO 3166-1: LU / LUX；电信区号 352） */
    LUXEMBOURG("LU", "卢森堡", "Luxembourg", "LUX", "352"),
    /** 拉脱维亚（ISO 3166-1: LV / LVA；电信区号 371） */
    LATVIA("LV", "拉脱维亚", "Latvia", "LVA", "371"),
    /** 利比亚（ISO 3166-1: LY / LBY；电信区号 218） */
    LIBYA("LY", "利比亚", "Libya", "LBY", "218"),
    /** 摩洛哥（ISO 3166-1: MA / MAR；电信区号 212） */
    MOROCCO("MA", "摩洛哥", "Morocco", "MAR", "212"),
    /** 摩纳哥（ISO 3166-1: MC / MCO；电信区号 377） */
    MONACO("MC", "摩纳哥", "Monaco", "MCO", "377"),
    /** 摩尔多瓦（ISO 3166-1: MD / MDA；电信区号 373） */
    MOLDOVA("MD", "摩尔多瓦", "Moldova", "MDA", "373"),
    /** 黑山（ISO 3166-1: ME / MNE；电信区号 382） */
    MONTENEGRO("ME", "黑山", "Montenegro", "MNE", "382"),
    /** 法属圣马丁（ISO 3166-1: MF / MAF；电信区号 590） */
    SAINT_MARTIN("MF", "法属圣马丁", "Saint Martin", "MAF", "590"),
    /** 马达加斯加（ISO 3166-1: MG / MDG；电信区号 261） */
    MADAGASCAR("MG", "马达加斯加", "Madagascar", "MDG", "261"),
    /** 马绍尔群岛（ISO 3166-1: MH / MHL；电信区号 692） */
    MARSHALL_ISLANDS("MH", "马绍尔群岛", "Marshall Islands", "MHL", "692"),
    /** 北马其顿（ISO 3166-1: MK / MKD；电信区号 389） */
    NORTH_MACEDONIA("MK", "北马其顿", "North Macedonia", "MKD", "389"),
    /** 马里（ISO 3166-1: ML / MLI；电信区号 223） */
    MALI("ML", "马里", "Mali", "MLI", "223"),
    /** 缅甸（ISO 3166-1: MM / MMR；电信区号 95） */
    MYANMAR("MM", "缅甸", "Myanmar", "MMR", "95"),
    /** 蒙古（ISO 3166-1: MN / MNG；电信区号 976） */
    MONGOLIA("MN", "蒙古", "Mongolia", "MNG", "976"),
    /** 中国澳门（ISO 3166-1: MO / MAC；电信区号 853） */
    MACAU("MO", "中国澳门", "Macau", "MAC", "853"),
    /** 北马里亚纳群岛（ISO 3166-1: MP / MNP；电信区号 1670） */
    NORTHERN_MARIANA_ISLANDS("MP", "北马里亚纳群岛", "Northern Mariana Islands", "MNP", "1670"),
    /** 马提尼克（ISO 3166-1: MQ / MTQ；电信区号 596） */
    MARTINIQUE("MQ", "马提尼克", "Martinique", "MTQ", "596"),
    /** 毛里塔尼亚（ISO 3166-1: MR / MRT；电信区号 222） */
    MAURITANIA("MR", "毛里塔尼亚", "Mauritania", "MRT", "222"),
    /** 蒙特塞拉特（ISO 3166-1: MS / MSR；电信区号 1664） */
    MONTSERRAT("MS", "蒙特塞拉特", "Montserrat", "MSR", "1664"),
    /** 马耳他（ISO 3166-1: MT / MLT；电信区号 356） */
    MALTA("MT", "马耳他", "Malta", "MLT", "356"),
    /** 毛里求斯（ISO 3166-1: MU / MUS；电信区号 230） */
    MAURITIUS("MU", "毛里求斯", "Mauritius", "MUS", "230"),
    /** 马尔代夫（ISO 3166-1: MV / MDV；电信区号 960） */
    MALDIVES("MV", "马尔代夫", "Maldives", "MDV", "960"),
    /** 马拉维（ISO 3166-1: MW / MWI；电信区号 265） */
    MALAWI("MW", "马拉维", "Malawi", "MWI", "265"),
    /** 墨西哥（ISO 3166-1: MX / MEX；电信区号 52） */
    MEXICO("MX", "墨西哥", "Mexico", "MEX", "52"),
    /** 马来西亚（ISO 3166-1: MY / MYS；电信区号 60） */
    MALAYSIA("MY", "马来西亚", "Malaysia", "MYS", "60"),
    /** 莫桑比克（ISO 3166-1: MZ / MOZ；电信区号 258） */
    MOZAMBIQUE("MZ", "莫桑比克", "Mozambique", "MOZ", "258"),
    /** 纳米比亚（ISO 3166-1: NA / NAM；电信区号 264） */
    NAMIBIA("NA", "纳米比亚", "Namibia", "NAM", "264"),
    /** 新喀里多尼亚（ISO 3166-1: NC / NCL；电信区号 687） */
    NEW_CALEDONIA("NC", "新喀里多尼亚", "New Caledonia", "NCL", "687"),
    /** 尼日尔（ISO 3166-1: NE / NER；电信区号 227） */
    NIGER("NE", "尼日尔", "Niger", "NER", "227"),
    /** 诺福克岛（ISO 3166-1: NF / NFK；电信区号 672） */
    NORFOLK_ISLAND("NF", "诺福克岛", "Norfolk Island", "NFK", "672"),
    /** 尼日利亚（ISO 3166-1: NG / NGA；电信区号 234） */
    NIGERIA("NG", "尼日利亚", "Nigeria", "NGA", "234"),
    /** 尼加拉瓜（ISO 3166-1: NI / NIC；电信区号 505） */
    NICARAGUA("NI", "尼加拉瓜", "Nicaragua", "NIC", "505"),
    /** 荷兰（ISO 3166-1: NL / NLD；电信区号 31） */
    NETHERLANDS("NL", "荷兰", "Netherlands", "NLD", "31"),
    /** 挪威（ISO 3166-1: NO / NOR；电信区号 47） */
    NORWAY("NO", "挪威", "Norway", "NOR", "47"),
    /** 尼泊尔（ISO 3166-1: NP / NPL；电信区号 977） */
    NEPAL("NP", "尼泊尔", "Nepal", "NPL", "977"),
    /** 瑙鲁（ISO 3166-1: NR / NRU；电信区号 674） */
    NAURU("NR", "瑙鲁", "Nauru", "NRU", "674"),
    /** 纽埃（ISO 3166-1: NU / NIU；电信区号 683） */
    NIUE("NU", "纽埃", "Niue", "NIU", "683"),
    /** 新西兰（ISO 3166-1: NZ / NZL；电信区号 64） */
    NEW_ZEALAND("NZ", "新西兰", "New Zealand", "NZL", "64"),
    /** 阿曼（ISO 3166-1: OM / OMN；电信区号 968） */
    OMAN("OM", "阿曼", "Oman", "OMN", "968"),
    /** 巴拿马（ISO 3166-1: PA / PAN；电信区号 507） */
    PANAMA("PA", "巴拿马", "Panama", "PAN", "507"),
    /** 秘鲁（ISO 3166-1: PE / PER；电信区号 51） */
    PERU("PE", "秘鲁", "Peru", "PER", "51"),
    /** 法属波利尼西亚（ISO 3166-1: PF / PYF；电信区号 689） */
    FRENCH_POLYNESIA("PF", "法属波利尼西亚", "French Polynesia", "PYF", "689"),
    /** 巴布亚新几内亚（ISO 3166-1: PG / PNG；电信区号 675） */
    PAPUA_NEW_GUINEA("PG", "巴布亚新几内亚", "Papua New Guinea", "PNG", "675"),
    /** 菲律宾（ISO 3166-1: PH / PHL；电信区号 63） */
    PHILIPPINES("PH", "菲律宾", "Philippines", "PHL", "63"),
    /** 巴基斯坦（ISO 3166-1: PK / PAK；电信区号 92） */
    PAKISTAN("PK", "巴基斯坦", "Pakistan", "PAK", "92"),
    /** 波兰（ISO 3166-1: PL / POL；电信区号 48） */
    POLAND("PL", "波兰", "Poland", "POL", "48"),
    /** 圣皮埃尔和密克隆（ISO 3166-1: PM / SPM；电信区号 508） */
    SAINT_PIERRE_AND_MIQUELON("PM", "圣皮埃尔和密克隆", "Saint Pierre and Miquelon", "SPM", "508"),
    /** 皮特凯恩群岛（ISO 3166-1: PN / PCN；电信区号 64） */
    PITCAIRN_ISLANDS("PN", "皮特凯恩群岛", "Pitcairn Islands", "PCN", "64"),
    /** 波多黎各（ISO 3166-1: PR / PRI；电信区号 1787） */
    PUERTO_RICO("PR", "波多黎各", "Puerto Rico", "PRI", "1787"),
    /** 巴勒斯坦（ISO 3166-1: PS / PSE；电信区号 970） */
    PALESTINE("PS", "巴勒斯坦", "Palestine", "PSE", "970"),
    /** 葡萄牙（ISO 3166-1: PT / PRT；电信区号 351） */
    PORTUGAL("PT", "葡萄牙", "Portugal", "PRT", "351"),
    /** 帕劳（ISO 3166-1: PW / PLW；电信区号 680） */
    PALAU("PW", "帕劳", "Palau", "PLW", "680"),
    /** 巴拉圭（ISO 3166-1: PY / PRY；电信区号 595） */
    PARAGUAY("PY", "巴拉圭", "Paraguay", "PRY", "595"),
    /** 卡塔尔（ISO 3166-1: QA / QAT；电信区号 974） */
    QATAR("QA", "卡塔尔", "Qatar", "QAT", "974"),
    /** 留尼汪（ISO 3166-1: RE / REU；电信区号 262） */
    REUNION("RE", "留尼汪", "Réunion", "REU", "262"),
    /** 罗马尼亚（ISO 3166-1: RO / ROU；电信区号 40） */
    ROMANIA("RO", "罗马尼亚", "Romania", "ROU", "40"),
    /** 塞尔维亚（ISO 3166-1: RS / SRB；电信区号 381） */
    SERBIA("RS", "塞尔维亚", "Serbia", "SRB", "381"),
    /** 俄罗斯（ISO 3166-1: RU / RUS；电信区号 7） */
    RUSSIA("RU", "俄罗斯", "Russia", "RUS", "7"),
    /** 卢旺达（ISO 3166-1: RW / RWA；电信区号 250） */
    RWANDA("RW", "卢旺达", "Rwanda", "RWA", "250"),
    /** 沙特阿拉伯（ISO 3166-1: SA / SAU；电信区号 966） */
    SAUDI_ARABIA("SA", "沙特阿拉伯", "Saudi Arabia", "SAU", "966"),
    /** 所罗门群岛（ISO 3166-1: SB / SLB；电信区号 677） */
    SOLOMON_ISLANDS("SB", "所罗门群岛", "Solomon Islands", "SLB", "677"),
    /** 塞舌尔（ISO 3166-1: SC / SYC；电信区号 248） */
    SEYCHELLES("SC", "塞舌尔", "Seychelles", "SYC", "248"),
    /** 苏丹（ISO 3166-1: SD / SDN；电信区号 249） */
    SUDAN("SD", "苏丹", "Sudan", "SDN", "249"),
    /** 瑞典（ISO 3166-1: SE / SWE；电信区号 46） */
    SWEDEN("SE", "瑞典", "Sweden", "SWE", "46"),
    /** 新加坡（ISO 3166-1: SG / SGP；电信区号 65） */
    SINGAPORE("SG", "新加坡", "Singapore", "SGP", "65"),
    /** 圣赫勒拿、阿森松和特里斯坦-达库尼亚（ISO 3166-1: SH / SHN；电信区号 290） */
    SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA("SH", "圣赫勒拿、阿森松和特里斯坦-达库尼亚", "Saint Helena, Ascension and Tristan da Cunha", "SHN", "290"),
    /** 斯洛文尼亚（ISO 3166-1: SI / SVN；电信区号 386） */
    SLOVENIA("SI", "斯洛文尼亚", "Slovenia", "SVN", "386"),
    /** 斯瓦尔巴和扬马延（ISO 3166-1: SJ / SJM；电信区号 4779） */
    SVALBARD_AND_JAN_MAYEN("SJ", "斯瓦尔巴和扬马延", "Svalbard and Jan Mayen", "SJM", "4779"),
    /** 斯洛伐克（ISO 3166-1: SK / SVK；电信区号 421） */
    SLOVAKIA("SK", "斯洛伐克", "Slovakia", "SVK", "421"),
    /** 塞拉利昂（ISO 3166-1: SL / SLE；电信区号 232） */
    SIERRA_LEONE("SL", "塞拉利昂", "Sierra Leone", "SLE", "232"),
    /** 圣马力诺（ISO 3166-1: SM / SMR；电信区号 378） */
    SAN_MARINO("SM", "圣马力诺", "San Marino", "SMR", "378"),
    /** 塞内加尔（ISO 3166-1: SN / SEN；电信区号 221） */
    SENEGAL("SN", "塞内加尔", "Senegal", "SEN", "221"),
    /** 索马里（ISO 3166-1: SO / SOM；电信区号 252） */
    SOMALIA("SO", "索马里", "Somalia", "SOM", "252"),
    /** 苏里南（ISO 3166-1: SR / SUR；电信区号 597） */
    SURINAME("SR", "苏里南", "Suriname", "SUR", "597"),
    /** 南苏丹（ISO 3166-1: SS / SSD；电信区号 211） */
    SOUTH_SUDAN("SS", "南苏丹", "South Sudan", "SSD", "211"),
    /** 圣多美和普林西比（ISO 3166-1: ST / STP；电信区号 239） */
    SAO_TOME_AND_PRINCIPE("ST", "圣多美和普林西比", "São Tomé and Príncipe", "STP", "239"),
    /** 萨尔瓦多（ISO 3166-1: SV / SLV；电信区号 503） */
    EL_SALVADOR("SV", "萨尔瓦多", "El Salvador", "SLV", "503"),
    /** 荷属圣马丁（ISO 3166-1: SX / SXM；电信区号 1721） */
    SINT_MAARTEN("SX", "荷属圣马丁", "Sint Maarten", "SXM", "1721"),
    /** 叙利亚（ISO 3166-1: SY / SYR；电信区号 963） */
    SYRIA("SY", "叙利亚", "Syria", "SYR", "963"),
    /** 斯威士兰（ISO 3166-1: SZ / SWZ；电信区号 268） */
    ESWATINI("SZ", "斯威士兰", "Eswatini", "SWZ", "268"),
    /** 特克斯和凯科斯群岛（ISO 3166-1: TC / TCA；电信区号 1649） */
    TURKS_AND_CAICOS_ISLANDS("TC", "特克斯和凯科斯群岛", "Turks and Caicos Islands", "TCA", "1649"),
    /** 乍得（ISO 3166-1: TD / TCD；电信区号 235） */
    CHAD("TD", "乍得", "Chad", "TCD", "235"),
    /** 法属南部领地（ISO 3166-1: TF / ATF；电信区号 262） */
    FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS("TF", "法属南部领地", "French Southern and Antarctic Lands", "ATF", "262"),
    /** 多哥（ISO 3166-1: TG / TGO；电信区号 228） */
    TOGO("TG", "多哥", "Togo", "TGO", "228"),
    /** 泰国（ISO 3166-1: TH / THA；电信区号 66） */
    THAILAND("TH", "泰国", "Thailand", "THA", "66"),
    /** 塔吉克斯坦（ISO 3166-1: TJ / TJK；电信区号 992） */
    TAJIKISTAN("TJ", "塔吉克斯坦", "Tajikistan", "TJK", "992"),
    /** 托克劳（ISO 3166-1: TK / TKL；电信区号 690） */
    TOKELAU("TK", "托克劳", "Tokelau", "TKL", "690"),
    /** 东帝汶（ISO 3166-1: TL / TLS；电信区号 670） */
    TIMOR_LESTE("TL", "东帝汶", "Timor-Leste", "TLS", "670"),
    /** 土库曼斯坦（ISO 3166-1: TM / TKM；电信区号 993） */
    TURKMENISTAN("TM", "土库曼斯坦", "Turkmenistan", "TKM", "993"),
    /** 突尼斯（ISO 3166-1: TN / TUN；电信区号 216） */
    TUNISIA("TN", "突尼斯", "Tunisia", "TUN", "216"),
    /** 汤加（ISO 3166-1: TO / TON；电信区号 676） */
    TONGA("TO", "汤加", "Tonga", "TON", "676"),
    /** 土耳其（ISO 3166-1: TR / TUR；电信区号 90） */
    TURKIYE("TR", "土耳其", "Türkiye", "TUR", "90"),
    /** 特立尼达和多巴哥（ISO 3166-1: TT / TTO；电信区号 1868） */
    TRINIDAD_AND_TOBAGO("TT", "特立尼达和多巴哥", "Trinidad and Tobago", "TTO", "1868"),
    /** 图瓦卢（ISO 3166-1: TV / TUV；电信区号 688） */
    TUVALU("TV", "图瓦卢", "Tuvalu", "TUV", "688"),
    /** 中国台湾省（ISO 3166-1: TW / TWN；电信区号 886） */
    TAIWAN("TW", "中国台湾省", "Taiwan", "TWN", "886"),
    /** 坦桑尼亚（ISO 3166-1: TZ / TZA；电信区号 255） */
    TANZANIA("TZ", "坦桑尼亚", "Tanzania", "TZA", "255"),
    /** 乌克兰（ISO 3166-1: UA / UKR；电信区号 380） */
    UKRAINE("UA", "乌克兰", "Ukraine", "UKR", "380"),
    /** 乌干达（ISO 3166-1: UG / UGA；电信区号 256） */
    UGANDA("UG", "乌干达", "Uganda", "UGA", "256"),
    /** 美国本土外小岛屿（ISO 3166-1: UM / UMI；电信区号 268） */
    UNITED_STATES_MINOR_OUTLYING_ISLANDS("UM", "美国本土外小岛屿", "United States Minor Outlying Islands", "UMI", "268"),
    /** 美国（ISO 3166-1: US / USA；电信区号 1） */
    UNITED_STATES("US", "美国", "United States", "USA", "1"),
    /** 乌拉圭（ISO 3166-1: UY / URY；电信区号 598） */
    URUGUAY("UY", "乌拉圭", "Uruguay", "URY", "598"),
    /** 乌兹别克斯坦（ISO 3166-1: UZ / UZB；电信区号 998） */
    UZBEKISTAN("UZ", "乌兹别克斯坦", "Uzbekistan", "UZB", "998"),
    /** 梵蒂冈（ISO 3166-1: VA / VAT；电信区号 3906698） */
    VATICAN_CITY("VA", "梵蒂冈", "Vatican City", "VAT", "3906698"),
    /** 圣文森特和格林纳丁斯（ISO 3166-1: VC / VCT；电信区号 1784） */
    SAINT_VINCENT_AND_THE_GRENADINES("VC", "圣文森特和格林纳丁斯", "Saint Vincent and the Grenadines", "VCT", "1784"),
    /** 委内瑞拉（ISO 3166-1: VE / VEN；电信区号 58） */
    VENEZUELA("VE", "委内瑞拉", "Venezuela", "VEN", "58"),
    /** 英属维尔京群岛（ISO 3166-1: VG / VGB；电信区号 1284） */
    BRITISH_VIRGIN_ISLANDS("VG", "英属维尔京群岛", "British Virgin Islands", "VGB", "1284"),
    /** 美属维尔京群岛（ISO 3166-1: VI / VIR；电信区号 1340） */
    UNITED_STATES_VIRGIN_ISLANDS("VI", "美属维尔京群岛", "United States Virgin Islands", "VIR", "1340"),
    /** 越南（ISO 3166-1: VN / VNM；电信区号 84） */
    VIETNAM("VN", "越南", "Vietnam", "VNM", "84"),
    /** 瓦努阿图（ISO 3166-1: VU / VUT；电信区号 678） */
    VANUATU("VU", "瓦努阿图", "Vanuatu", "VUT", "678"),
    /** 瓦利斯和富图纳（ISO 3166-1: WF / WLF；电信区号 681） */
    WALLIS_AND_FUTUNA("WF", "瓦利斯和富图纳", "Wallis and Futuna", "WLF", "681"),
    /** 萨摩亚（ISO 3166-1: WS / WSM；电信区号 685） */
    SAMOA("WS", "萨摩亚", "Samoa", "WSM", "685"),
    /** 也门（ISO 3166-1: YE / YEM；电信区号 967） */
    YEMEN("YE", "也门", "Yemen", "YEM", "967"),
    /** 马约特（ISO 3166-1: YT / MYT；电信区号 262） */
    MAYOTTE("YT", "马约特", "Mayotte", "MYT", "262"),
    /** 南非（ISO 3166-1: ZA / ZAF；电信区号 27） */
    SOUTH_AFRICA("ZA", "南非", "South Africa", "ZAF", "27"),
    /** 赞比亚（ISO 3166-1: ZM / ZMB；电信区号 260） */
    ZAMBIA("ZM", "赞比亚", "Zambia", "ZMB", "260"),
    /** 津巴布韦（ISO 3166-1: ZW / ZWE；电信区号 263） */
    ZIMBABWE("ZW", "津巴布韦", "Zimbabwe", "ZWE", "263");

    /** ISO 3166-1 alpha-2 主码 */
    private final String code;

    /** 中文名称 */
    private final String label;

    /** 英文名称 */
    private final String enName;

    /** ISO 3166-1 alpha-3 别名码 */
    private final String alpha3;

    /** 电信区号（ITU-T E.164 国家/地区码，不含国际冠字，如 86；北美编号计划 1、俄/哈 7 为多国共享码） */
    private final String idd;

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

    /**
     * 严格查找：按电信区号匹配，未匹配抛 {@link com.asialjim.standard4j.exception.BusinessException}。
     *
     * <p>入参兼容 {@code "86"}、{@code "+86"}、{@code "0086"}（剥去 {@code +} 与
     * 国际冠字 {@code 00}）。注意区号存在多国共享（北美编号计划 {@code 1}、俄/哈 {@code 7}），
     * 共享码返回枚举序首个命中；无歧义匹配请用 alpha-2/alpha-3 码。</p>
     *
     * @param idd 电信区号
     * @return {@link CountryOrRegion}
     */
    public static CountryOrRegion ofIdd(String idd) {
        return ofIddNullable(idd)
                .orElseThrow(() -> new com.asialjim.standard4j.exception.BusinessException(
                        com.asialjim.standard4j.exception.SystemResCode.UNMATCHED_CODE,
                        "未知的国家或地区电信区号: [" + idd + "]"));
    }

    /**
     * 宽容查找：按电信区号匹配（兼容 {@code "86"}/{@code "+86"}/{@code "0086"}；
     * 共享码返回枚举序首个命中）
     *
     * @param idd 电信区号
     * @return {@link Optional}&lt;{@link CountryOrRegion}&gt;
     */
    public static Optional<CountryOrRegion> ofIddNullable(String idd) {
        return ofIddAll(idd).stream().findFirst();
    }

    /**
     * 宽容解析：按电信区号匹配，未匹配一律返回 {@link #UNKNOWN}
     *
     * @param idd 电信区号
     * @return {@link CountryOrRegion}
     */
    public static CountryOrRegion ofIddOrDefault(String idd) {
        return ofIddNullable(idd).orElse(UNKNOWN);
    }

    /**
     * 按电信区号匹配全部命中项（区号存在多国共享，如 {@code 1}/{@code 7}）。
     *
     * @param idd 电信区号（兼容 {@code "86"}/{@code "+86"}/{@code "0086"}）
     * @return 命中项列表，不可变；无命中返回空列表
     */
    public static List<CountryOrRegion> ofIddAll(String idd) {
        if (idd == null)
            return Collections.emptyList();
        String normalized = idd.trim();
        if (normalized.startsWith("+"))
            normalized = normalized.substring(1);
        // 剥去国际冠字 "00"（E.164 国家码不以 0 开头，连续剥 0 安全；至少保留 1 位）
        int i = 0;
        while (i < normalized.length() - 1 && normalized.charAt(i) == '0')
            i++;
        normalized = normalized.substring(i);
        if (normalized.isEmpty())
            return Collections.emptyList();
        List<CountryOrRegion> matched = new ArrayList<>();
        for (CountryOrRegion value : values()) {
            if (value.idd.equals(normalized))
                matched.add(value);
        }
        return Collections.unmodifiableList(matched);
    }
}