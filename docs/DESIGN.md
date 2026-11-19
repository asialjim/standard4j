# standard4j 设计文档

> 对齐原则：国标（GB）优先，其次 ISO，最后才自定义编码。

## 1. 统一字典模型（`code`）

所有标准代码统一实现：

```java
public interface Code {
    String code();     // 编码
    String label();    // 展示名（不可用 name()，与 Enum.name() 冲突）
    default String description() { return label(); }
    default boolean enabled() { return true; }
}
```

查找约定（每个字典枚举统一提供）：

- `of(String code)` —— 严格匹配，找不到抛标准异常；
- `ofNullable(String code)` —— 返回 `Optional`；
- `ofOrDefault(String code)` —— 宽容解析，未匹配返回 `UNKNOWN`；
- 枚举常量名 + code 都不区分大小写。

数据策略：小字典纯封闭枚举；大数据集**只定义契约接口 + 仓库接口，不内置数据**（谁使用谁实现，见 §1ter）。

**未知码值策略（已定）**：

- 每个封闭字典枚举**必须包含 `UNKNOWN`（未提供/未知）项**；
- 两套查找口径并存，语义分开：
  - `of(code)` —— **严格**查找，未匹配抛标准异常（用于必须合法的场景）；
  - `ofOrDefault(code)` —— **宽容**解析，未匹配返回 `UNKNOWN`（用于展示、反序列化兜底）；
- 每个枚举提供 `unknown()` 静态访问器；`UNKNOWN` 的 code 值由各字典依据其标准定义（如性别 GB/T 2261.1 的 `0=未知`、`9=未说明`），无标准依据的统一用 `"-"`。

**序列化与反序列化约定（已定，三层口径）**：

- **存储口径（DB）**：只存 `code` 字符串（TypeHandler 等）；存储结构化数据无意义；
- **展示口径（给前端的报文）**：输出**结构化视图** `CodeView{code, label, description}`——前端用 `label` 做阅读友好渲染、用 `code` 回传，杜绝"只有展示数据没有 code"的断链；core 提供 `record CodeView` 与 `Code.view()` 默认方法；
- **回传/交换口径（前端→后端）**：反序列化**兼容双形态**——字符串（按 code 解析）或对象（取其 `code` 成员解析），再走 `ofOrDefault` 宽容兜底（未知码 → `UNKNOWN`，不抛异常）；
- 强校验场景由业务点显式调 `of(code)` 自行抛错，序列化层永远宽容；
- `UNKNOWN` 亦序列化为其 code（如 `"-"`），保证往返幂等；
- 本库核心只定义约定与 `CodeCodec` SPI 接口；**Jackson 适配（含 `@CodeRef` 注解声明"此字段按 CodeView 输出/按存储口径输出"）由 util4j 实现**（util4j → standard4j，依赖方向成立），本库本体保持零依赖，其他 JSON 框架按同一约定自行适配。

## 1bis. 证照类型标准（`certificate`）

**以 GB/T 36901~36904-2018（电子证照系列国标）为准**：

- `CertificateType`：目录代码、证照名称、发证机构、发证日期/有效期元数据；证照标识按 **GB/T 36904-2018** 规则生成（OID 形式，如 `1.2.156.3005.2.x`）。
- **数据策略（已定）：常用证照做封闭枚举，冷门证照走文件数据驱动**：
  - 枚举内置高频证照项（营业执照、居民户口簿、结婚证、学历证书等）；
  - 冷门/行业专属证照由目录文件补充：默认内置 classpath JSON（GB/T 36901 目录代码），使用方可注册自定义目录文件；
  - 统一经 Facade 查找：`of(code)` 先查枚举、再查文件目录，别名解析同理，对外无感知。
- 与 `IdCardType`（身份证件类型）是两个正交标准，**不做字段关联**：一个自然人可持有多本证件，
  自然人基础字段只保留姓名/性别/出生日期等基础信息，证件由独立集合承载。
- 当前状态：`certificate` 包尚未实现，概念与数据来源待需求方确认。

## 1ter. 字典仓库契约（`code`）

枚举字典靠静态 `of()` 查询；**非枚举实现**（文件驱动、数据库字典表、外部服务）由仓库接口约束成同一套查询口径，使"枚举还是文件"对调用方无感：

```java
public interface CodeRepository<T extends Code> {
    T unknown();                            // 宽容解析兜底项
    Optional<T> ofNullable(String code);    // 实现方必须提供
    default T of(String code);              // 未命中抛 BusinessException(UNMATCHED_CODE)
    default T ofOrDefault(String code);     // 未命中落 unknown()
}

public interface HierarchicalCodeRepository<T extends HierarchicalCode> extends CodeRepository<T> {
    Optional<T> parentOf(String code);      // 默认实现基于 parentCode()
    List<T> childrenOf(String parentCode);  // 实现方提供
}
```

- 层级字典项统一实现 `HierarchicalCode extends Code`（`int level()` + `String parentCode()`，顶级 `parentCode()` 为 `null`）；
- 领域仓库只做类型收窄：`AdministrativeDivisionRepository`、`IndustryRepository`、`OccupationRepository`；
- 枚举字典也可顺手实现仓库接口（内部委托 `Codes`），便于统一注入。

序列化与别名扩展点：

```java
public interface CodeCodec {                 // 序列化编解码 SPI，Jackson 适配在 util4j
    String encode(Code code);                // 默认取 code()
    <T extends Code> Optional<T> decode(String code, T[] values); // 默认委托 Codes
}

public interface AliasTable<T extends Code> { // 别名映射 SPI，数据可来自文件/DB/代码
    String namespace();                       // 如 "JR/T-0160"
    Optional<T> ofNullable(String alias);
    default T of(String alias);
    default T ofOrDefault(String alias, T unknown);
}
```

- `AliasTables.ofNullable(values, input, tables...)` 先查主码、再按顺序查别名表；
  主码与别名冲突时主码优先；需要按特定行业别名强制解析时使用枚举自带的显式反查
  （如 `IdCardType.ofFinancialCode`）。

## 2. 业务异常体系（`exception`）

沿用 commons-common 的设计（该设计可取），重写注释、剥离 Spring 依赖：

```java
public interface ResCode extends Code {
    int status();          // HTTP 语义状态码
    String code();         // 业务错误码（字符串）
    String label();        // 展示名（Code 契约）
    default String msg();  // 默认消息
    default BusinessException ex();                // 构建业务异常
    default BusinessException ex(String message);
    default void thr();                            // 直接抛出
    default void thr(String message);
}
```

- `BusinessException extends RuntimeException implements ResCode` —— 用户可见的业务错误；
- `SystemException` —— 系统内部错误；
- `SimpleResCode` —— 运行时动态码值（如从 `CODE|MESSAGE` 结构化消息解析后构造）；
- 不提供 `UnLoginException`：未登录属于业务/接入层错误，由使用方自定义 `ResCode`；
- `SystemResCode` —— 内置**系统级**错误码枚举：**只收录系统自身故障类错误**（`UNKNOWN`、`SYSTEM_BUSY(-2, 503)`、`INTERNAL_ERROR("500")`、`UNMATCHED_CODE("STD-00001")`、`CURRENCY_MISMATCH("STD-00002")`）；因用户造成的错误（请求非法/未登录/无权访问/资源不存在等 HTTP 4xx 语义）**不在系统码收录**，由业务/接入层自定义 `ResCode` 枚举；
- 错误码规范：字符串码，模块前缀 + 数字（如 `"STD-00001"`）；错误码枚举同样实现 `Code` 字典接口；
- 统一响应体 `Result` 与分页响应元数据 `PageMeta` 已落在 `result` 包：`PageMeta` 只含当前页、总页数、当前页大小、总记录数；**分页请求参数不放标准库**。

## 3. 命名标准（`entity`）

### 3.1 业务字段规范（全部以接口落实，getter/setter 契约）

**所有字段命名标准一律用接口限定**（不用抽象类），粒度最小化、可组合，实体按需实现：

- `Nameable` —— 姓名、曾用名
- `Genderable` —— 性别（类型绑定 `Gender` 枚举，非 String）
- `BirthDateable` —— 出生日期（`LocalDate`）；年龄为推导字段（按周岁），不落存储
- `Ethnicable` —— 民族（绑定 `EthnicGroup`）
- `Certifiable` —— 证件类型（绑定 `IdCardType`）、证件号码、证件名称、发证机关、有效期起止；描述**单本证件**，不是自然人基础字段（一个自然人可持有多本证件）
- `Contactable` —— 手机号、座机、邮箱
- `Addressable` —— 省/市/区县代码（`String`；区划数据量大且逐年变更，不在枚举中固化，见 §7）+ 详址
- `Organizationable` —— 组织名称、统一社会信用代码、法定代表人

硬规则：
1. 接口可组合：自然人 = `Nameable + Genderable + BirthDateable + Certifiable + Contactable + Addressable + Auditable + ...`，按需拼装，规避抽象类单继承限制；
2. 字段的 Java 类型优先绑定标准字典枚举（`Gender`/`IdCardType`/`EthnicGroup`）；区划代码使用 `String`
   （区划字典只定义契约、不内置数据，枚举无法承载 3000+ 且逐年变更的码表），码值合法性由使用方的
   `AdministrativeDivisionRepository` 校验；
3. getter/setter 命名即标准：跨项目同名字段同语义，序列化/映射天然对齐。

### 3.2 实体元数据标准（接口限定，getter/setter）

通用技术字段不用"扩展类"，而是用**带 getter/setter 的接口**限定——任何实体实现接口即获得标准字段契约：

```java
public interface Auditable {                          // 审计字段
    String getCreatedBy();  void setCreatedBy(String createdBy);
    LocalDateTime getCreatedAt();  void setCreatedAt(LocalDateTime createdAt);
    String getUpdatedBy();  void setUpdatedBy(String updatedBy);
    LocalDateTime getUpdatedAt();  void setUpdatedAt(LocalDateTime updatedAt);
}
public interface Versionable {                        // 乐观锁版本
    Long getVersion();  void setVersion(Long version);
}
public interface SoftDeletable {                      // 逻辑删除
    Boolean getDeleted();  void setDeleted(Boolean deleted);
}
public interface LifeCycle<S extends Code> {          // 生命周期状态（状态字典由业务方定义）
    S getState();  void setState(S state);
}
public interface Treeable {                           // 树形层级（行政区划/组织树）
    String getPath();  void setPath(String path);     // "/440000/440300/440304"，含自身
    Integer getLevel();  void setLevel(Integer level); // 从 1 起计，顶级为 1
    String getParentId();  void setParentId(String parentId);
}
```

- **生命周期状态不内置字典**：通用状态在不同业务域语义差异过大，`LifeCycle<S extends Code>` 由业务方定义状态枚举绑定，码值合法性由类型系统保证；
- `BirthDateable` 提供周岁推导：`ageOn(LocalDate)` / `age()` 返回 `OptionalInt`，规则为"未过生日不加岁"，2 月 29 日出生者平年按 2 月 28 日计；
- `Certifiable` 提供 `certificateValidOn(LocalDate)`：起止日均有效，`validTo` 为空视为长期有效；
- 统一字段命名（`createdAt/createdBy/...`），杜绝各项目各写一套；
- **业务单号格式规范**：`前缀 + 日期 + 序列`（与错误码规范同族），单号前缀同样注册为标准字典；具体前缀字典与校验规则**待需求方确定**。

## 4. 校验器标准（`validate`）

统一校验器接口（只判断与给原因，不做脱敏）：

```java
public interface Validator<T> {
    ValidationResult validate(T value);   // 通过/不通过 + 错误原因
}
```

内置校验器清单：

| 校验器 | 依据 |
|---|---|
| 公民身份号码（18 位 + MOD 11-2 校验码 + 出生日期段） | **GB 11643-1999** |
| 统一社会信用代码（18 位 + 校验位） | **GB 32100-2015** |
| 组织机构代码（旧 9 位 + 校验位） | **GB 11714-1997** |
| 国际电话号码模型（国家码 + 号码，格式规范） | **ITU-T E.164** |
| 银行卡号（Luhn 算法 + BIN） | **ISO/IEC 7812** |
| 中国手机号（11 位 + 号段合法性） | 工信部号段表 |
| 邮箱、车牌号、港澳台证件号格式 | 自定 |

实现入口：`Validators`（`ID_CARD`、`UNIFIED_SOCIAL_CREDIT_CODE`、`ORGANIZATION_CODE`、
`E164_PHONE`、`BANK_CARD`、`MOBILE_PHONE`、`EMAIL`、`LICENSE_PLATE`、`HONG_KONG_ID`、
`MACAO_ID`、`TAIWAN_ID`）。校验器只判断与给原因，不做脱敏；失败原因不含错误码。

## 5. 金额标准（`money`）

- `Currency`：**ISO 4217**（2024-06-25 官方发布版）**现行全量 179 个代码**封闭枚举——三位字母码 + 三位数字码 + 最小货币单位精度 `minorUnit`（人民币/美元 `2`、日元 `0`、XAU/XDR 等资金类 `-1` 表示无小数）；`of(code)` / `ofNullable` / `ofOrDefault` + 按数字码 `ofNumericCode*` 两套查找；
  - **区分两个"未知"**：`UNKNOWN`（`"-"`，本库约定的解析兜底项）≠ `XXX`（数字码 `999`，ISO 4217 官方"无货币"码）；
  - 中英文名取 CLDR；UYW/ZWG 为 2024 新增码，中文译名待官方数据校准。
- `Money` 模型：`BigDecimal amount` + `Currency`，**不可变 record**；
  - 硬性规定：**库内及使用规范一律不得用 double/float 表示金额**；
  - 构造时按币种精度规约小数位，多出精度用 `RoundingMode.UNNECESSARY` 直接抛错（**不静默舍入**，防资损）；
  - 最小单位换算：`ofMinor(long, Currency)` ⇄ `toMinor()`（分 ↔ 元）；
  - 同币种 `add`/`subtract`/`compareTo`，跨币种运算抛 `BusinessException(SystemResCode.CURRENCY_MISMATCH)`。

## 6. 计量单位标准（`unit`）

- 计量单位字典与换算：**GB 3100/3101/3102**（国际单位制及使用规则）；
- 常用领域单位（质量、长度、面积、体积、存储容量等）封闭枚举 + 冷门文件驱动；
- 统一 `Unit` 模型：code、名称、量纲（dimension）、换算基准。

## 7. 区域标准（`region`）

- 国家/地区 `CountryOrRegion`：ISO 3166-1 alpha-2（主键）+ alpha-3 + 中文名 + 英文名 + 电话区号（见 §9 表）。
- **行政区划 `AdministrativeDivision` 只定义契约，不内置数据**（GB/T 2260 省市区县 6 位、GB/T 10114 乡镇级 9 位；数据量 3000+ 且逐年变更）：
  - 契约接口 `AdministrativeDivision extends HierarchicalCode`（`level()` + `parentCode()`）；
  - 查询接口 `AdministrativeDivisionRepository extends HierarchicalCodeRepository`——`of`/`ofNullable`/`ofOrDefault` 三口径 + `parentOf`/`childrenOf`；
  - 谁使用谁实现（封闭枚举 / 文件驱动 / 数据库字典表皆可），并自行负责与最新版标准对齐。

## 8. 国际化（`i18n`）

- 语言 `Language`：**ISO 639-1** 两字母码，只收录**总使用人数（母语 + 第二语言）超过 5000 万**的常用语言（24 项：zh/en/hi/es/fr/ar/bn/pt/ru/ur/id/de/ja/sw/mr/te/ta/tr/vi/ko/it/fa/ha/pa）；
  - 汉语按宏观语种 `zh` 单列（粤语/吴语等变体无 ISO 639-1 码）；
  - 爪哇语等超 5000 万但无 ISO 639-1 码的语言不收录；
  - 常用子集而非全量，小语种由使用方自行扩展。
- 货币 ISO 4217 见 §5；时区 IANA、Locale 约定——待定，暂不纳入。

## 9. 人本字典（`human`）与行业字典

纯封闭枚举（均为小字典）：

| 字典 | 依据 |
|---|---|
| 性别 | **GB/T 2261.1**：`0`=未知、`1`=男、`2`=女、`9`=未说明 |
| 婚姻状况 | **GB/T 2261.2-2003**：2 位层次码 `10`未婚、`20`已婚（`21`初婚/`22`再婚/`23`复婚）、`30`丧偶、`40`离婚、`90`未说明；标准自带 `90` 兼任 UNKNOWN |
| 政治面貌 | **GB/T 4762**（GB 4762-1984）：2 位码 01-13，中共党员…群众；标准无未知码，UNKNOWN 按约定取 `"-"` |
| 民族 | **GB/T 3304**（2 位数字码 01-56）+ 旧库世界族裔 63 项（**非国标扩展**，code 加 `X-` 前缀如 `X-ARA`）；旧库三字码降为 `legacyCode` 辅助别名（旧码有 EVE/JIN 重复，已消歧），仅供历史数据迁移 |
| 学历 | **GB/T 4658-2006**：2 位层次码，末位 `*1`毕业/`*2`结业/`*3`肄业；`90`其他兼任 UNKNOWN；不收录私有扩展 |
| 学位 | **GB/T 6864-2003**：两层码，第一层 `1`名誉博士/`2`博士/`3`硕士/`4`学士，第二层 `01~40` 学科门类（12 门类）、`41~80` 专业学位（2003 文本 16 个：245/248/250、341-352、444）；2003 后新增学位（艺术学 213/313/413 等）暂不收录，按官方补充文件增补 |
| 健康状况 | **不设字典**（无权威国标，且业务口径差异过大） |
| 国籍/国家 | **ISO 3166-1** 全量 249 个正式分配的 alpha-2 码（`region` 包 `CountryOrRegion`，`human` 引用）+ alpha-3 别名码（`ofAlpha3*`）+ 电信区号 `idd`（ITU-T E.164 国家/地区码，`ofIdd*` 兼容 `86`/`+86`/`0086` 三种入参形态；`+1`/`+7` 多国共享码用 `ofIddAll` 返回全部命中；AQ/HM 无区号存空串）；中文译名取 CLDR-zh（简体），HK/MO 按数据集 TW 命名风格归一为"中国香港/中国澳门"；XK（科索沃）为用户自定义码暂不收录；译名与 GB/T 2659 官方译名可能存在个别差异，按官方数据校准 |
| 证件类型 | **GA/T 2000.156-2016** 主码 + **JR/T 0160-2018**（金融 DM001）内置别名（`ofFinancialCode*` 反查）+ 脱敏保留位数；证件类型与证照目录正交，不做字段关联 |
| 运营商 | **不设字典**（携号转网后运营商归属不再稳定，且无权威国标；号码段判定属业务能力，由使用方实现） |
| 行业 | **GB/T 4754** 国民经济行业分类——**只定义契约，不内置数据**：`Industry extends HierarchicalCode`（门类 A~T → 大类 2 位 → 中类 4 位 → 小类 6 位）+ `IndustryRepository`（三口径 + `parentOf`/`childrenOf`），谁使用谁实现 |
| 职业 | **GB/T 6565** 职业分类——**只定义契约，不内置数据**：`Occupation extends HierarchicalCode` + `OccupationRepository`，谁使用谁实现 |

## 10. 脱敏规则接口（`sensitive`）

契约与默认算法在 standard4j，特殊算法可由 sensitive4j 通过 `Masker` SPI 覆盖：

```java
public interface MaskRule extends Code {        // 规则标识 = 字典项，含脱敏元数据
    int sensitiveLeft();                        // 敏感段左侧保留字符数
    int sensitiveRight();                       // 敏感段右侧保留字符数
    default char maskChar() { return '*'; }     // 掩码字符
    default SensitiveSegment sensitiveSegment(String rawValue) {
        return SensitiveSegment.whole(rawValue); // 默认整段敏感
    }
    default String maskedValue(String rawValue); // 只替换敏感段，段外原样保留
}
public record SensitiveSegment(int start, int end) { } // 左闭右开区间
public interface Maskable {                     // 任意对象声明"我该怎么脱敏"
    String rawValue();
    MaskRule maskRule();                        // null = 不脱敏
    default String maskedValue(Masker masker);  // 空值/null 规则/不支持 均原样返回
}
public interface Masker {                       // SPI；内置 STANDARD 与 NONE
    boolean supports(MaskRule rule);
    String mask(String rawValue, MaskRule rule);
}
```

- **先定位敏感数据段，再段内掩码**：非敏感段原样保留。典型例子是邮箱
  `zhangsan@example.com`，只有 `@` 前的本地部分敏感，`@example.com` 不脱敏；
- 段内规则：左侧保留 `sensitiveLeft` 个字符、右侧保留 `sensitiveRight` 个字符，
  中间全部替换为 `maskChar()`；敏感段长度不足时至少掩码 1 个字符（2 字姓名 1/1 → `a*`）；
- 掩码字符默认 `*`；旧算法参考：身份证 6/4、手机号 3/4、银行卡 4/4、姓名 1/1、邮箱 1/0；
- `IdCardType` 直接实现 `MaskRule`，证件类型自带脱敏规则；`CommonMaskRule` 提供
  身份证/手机号/银行卡/姓名/邮箱等常用规则；
- `Masker.STANDARD` 使用规则默认算法；`Masker.NONE` 为 no-op 兜底，未装配实现时不会中断业务链路。

## 11. 从 commons-common 的借鉴清单

借鉴（设计）：`ResCode`、`BusinessException`/`SystemException`/`SystemResCode`、`IdCardType`（含脱敏元数据）、`CountryOrDict`、民族字典、`SensitiveUtils` 算法、pom 依赖坐标（commons-lang3/collections4/io、Jackson 3 `tools.jackson.*`、jakarta、Lombok）。

修正：
1. `Gender` 码值/名称对调 bug → 按 GB/T 2261.1 重定；
2. `Nationality` 实为民族 → 拆为 `EthnicGroup`（民族）与国家/国籍两个标准；
3. `ResCode` 机翻 javadoc → 全部重写；
4. Spring Boot 强耦合（spring.factories/自动装配）→ 不迁移，standard4j 保持零框架依赖。

不迁移：`App/CommonBean`、`event`、`MamsSession*`、`CacheNameAndTTL*`（运行时/框架能力，非本库职责）。

## 12. 待定项

- [x] 统一响应体 `Result` 与分页模型：已落在 standard4j `result` 包；分页请求参数不纳入标准库；
- [ ] 证件类型代码：主码 GA/T 2000.156-2016（无 GB 国标），需持标准全文核对旧码值；
- [ ] 证照目录（GB/T 36901）与 `CertificateType` 的取舍——概念待与需求方确认后再定；证照与证件类型正交，一个自然人可持有多本证件；
- [ ] 平台标准 / 平台应用标准的具体平台码值（契约与仓库接口已定义，数据由需求方提供）；
- [ ] 业务单号格式规范（前缀 + 日期 + 序列）的具体前缀字典与校验规则——需求方暂未确定；
- [ ] 日期/时间表述约定（存储时区、年龄周岁计算规范）——候选，未纳入范围。
