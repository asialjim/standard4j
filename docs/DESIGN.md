# standard4j 设计文档

> 对齐原则：国标（GB）优先，其次 ISO，最后才自定义编码。

## 1. 统一字典模型（`code`）

所有标准代码统一实现：

```java
public interface Code {
    String code();     // 编码
    String name();     // 标准名称
    default String description() { return name(); }
    default boolean enabled() { return true; }
}
```

查找约定（每个字典枚举统一提供）：

- `of(String code)` —— 严格匹配，找不到抛标准异常；
- `ofNullable(String code)` —— 返回 `Optional`；
- 枚举常量名 + code 都不区分大小写。

数据策略：小字典纯封闭枚举；大数据集常用项枚举 + 冷门项文件驱动（classpath JSON，可注册自定义文件），统一 Facade 查找。

## 1bis. 证照类型标准（`certificate`）

**以 GB/T 36901~36904-2018（电子证照系列国标）为准**：

- `CertificateType`：目录代码、证照名称、发证机构、发证日期/有效期元数据；证照标识按 **GB/T 36904-2018** 规则生成（OID 形式，如 `1.2.156.3005.2.x`）。
- **数据策略（已定）：常用证照做封闭枚举，冷门证照走文件数据驱动**：
  - 枚举内置高频证照项（营业执照、居民户口簿、结婚证、学历证书等）；
  - 冷门/行业专属证照由目录文件补充：默认内置 classpath JSON（GB/T 36901 目录代码），使用方可注册自定义目录文件；
  - 统一经 Facade 查找：`of(code)` 先查枚举、再查文件目录，别名解析同理，对外无感知。
- 与 `IdCardType`（身份证件类型）是两个正交标准：证件类型枚举项通过可空的 `certificateTypeCode` 字段关联证照目录代码。

## 2. 业务异常体系（`exception`）

沿用 commons-common 的设计（该设计可取），重写注释、剥离 Spring 依赖：

```java
public interface ResCode {
    int status();          // HTTP 语义状态码
    String code();         // 业务错误码（字符串）
    String msg();          // 默认消息
    boolean thr();         // 是否需要抛出
    // 工厂方法：result() / result(data) / resultErrs() / page(...) / ex() / thr()
}
```

- `BusinessException extends RuntimeException implements ResCode` —— 用户可见的业务错误；
- `SystemException` —— 系统内部错误；
- `UnLoginException` —— 未登录；
- `SystemResCode` —— 内置系统错误码枚举（旧库仅 `SysBusy(-2, "系统繁忙")`，逐步扩充）；
- 错误码规范：字符串码，模块前缀 + 数字（如 `"STD-00001"`）；错误码枚举同样实现 `Code` 字典接口。
- 统一响应体 `Result` / 分页 `PageParameter`/`PageData` 的**归属待定**（standard4j 或 util4j）。

## 3. 命名标准（`entity`）

### 3.1 业务字段规范（全部以接口落实，getter/setter 契约）

**所有字段命名标准一律用接口限定**（不用抽象类），粒度最小化、可组合，实体按需实现：

- `Nameable` —— 姓名、曾用名
- `Genderable` —— 性别（类型绑定 `Gender` 枚举，非 String）
- `BirthDateable` —— 出生日期（`LocalDate`）；年龄为推导字段（按周岁），不落存储
- `Ethnicable` —— 民族（绑定 `EthnicGroup`）
- `Certifiable` —— 证件类型（绑定 `IdCardType`）、证件号码、证件名称、发证机关、有效期起止
- `Contactable` —— 手机号、座机、邮箱
- `Addressable` —— 省/市/区县代码（绑定区划字典）+ 详址
- `Organizationable` —— 组织名称、统一社会信用代码、法定代表人

硬规则：
1. 接口可组合：自然人 = `Nameable + Genderable + BirthDateable + Certifiable + Contactable + Addressable + Auditable + ...`，按需拼装，规避抽象类单继承限制；
2. 字段的 Java 类型直接绑定标准字典枚举（`Gender`/`IdCardType`/`EthnicGroup`/区划代码），码值合法性由类型系统保证；
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
public interface LifeCycle {                          // 生命周期状态
    LifeCycleState getState();  void setState(LifeCycleState state);
}
public interface Treeable {                           // 树形层级（行政区划/组织树）
    String getPath();  void setPath(String path);
    Integer getLevel();  void setLevel(Integer level);
    String getParentId();  void setParentId(String parentId);
}
```

- `LifeCycleState`：通用生命周期字典（草稿/生效/停用/注销），实现 `Code`；
- 统一字段命名（`createdAt/createdBy/...`），杜绝各项目各写一套；
- **业务单号格式规范**：`前缀 + 日期 + 序列`（与错误码规范同族），单号前缀同样注册为标准字典。

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
| 中国手机号（11 位 + 号段合法性，与运营商字典联动） | 工信部号段表 |
| 邮箱、车牌号、港澳台证件号格式 | 自定 |

## 5. 金额标准（`money`）

- `Money` 模型：`BigDecimal amount` + `Currency`（ISO 4217 字典），**不可变**；
- 硬性规定：**库内及使用规范一律不得用 double/float 表示金额**；
- 最小单位（分/最小币值单位）与展示格式的转换约定。

## 6. 计量单位标准（`unit`）

- 计量单位字典与换算：**GB 3100/3101/3102**（国际单位制及使用规则）；
- 常用领域单位（质量、长度、面积、体积、存储容量等）封闭枚举 + 冷门文件驱动；
- 统一 `Unit` 模型：code、名称、量纲（dimension）、换算基准。

## 7. 区域标准（`region`）

- 国家/地区：ISO 3166-1 alpha-2（主键）+ alpha-3 + 数字码 + 电话区号 + 中英文名。
- 行政区划：GB/T 2260 省市区县（6 位）、GB/T 10114 乡镇级；**数据逐年变化**：省级封闭枚举 + 市/县级文件驱动，支持版本化更新。

## 8. 国际化（`i18n`）

- 语言 ISO 639-1、货币 ISO 4217（含货币符号、最小单位）、时区 IANA、Locale 约定。

## 9. 人本字典（`human`）与行业字典

纯封闭枚举（均为小字典）：

| 字典 | 依据 |
|---|---|
| 性别 | **GB/T 2261.1**：`0`=未知、`1`=男、`2`=女、`9`=未说明 |
| 婚姻状况 | **GB/T 2261.2** |
| 政治面貌 | **GB/T 4762** |
| 民族 | **GB/T 3304**（2 位数字码；旧三字码降为辅助别名） |
| 学历 | **GB/T 4658** |
| 学位 | 自定/现行学位授予口径 |
| 健康状况 | 自定 |
| 国籍/国家 | ISO 3166-1（归 `region`，`human` 引用） |
| 证件类型 | **GA/T 2000.156-2016** 主码 + **JR/T 0160-2018**（金融 DM001）内置别名 + `certificateTypeCode` 关联证照目录（可空）+ 脱敏保留位数 |
| 运营商 | 自定（移动/联通/电信/广电，与手机号校验联动） |
| 行业 | **GB/T 4754** 国民经济行业分类：常用类目枚举 + 全量文件驱动 |
| 职业 | **GB/T 6565** 职业分类：常用枚举 + 文件驱动 |

## 10. 脱敏规则接口（`sensitive`）

接口在 standard4j，实现（含旧 `SensitiveUtils` 算法）在 sensitive4j：

```java
public interface MaskRule extends Code { }   // 规则标识 = 字典项，含掩码元数据
public interface Maskable {                  // 任意对象声明"我该怎么脱敏"
    MaskRule maskRule();                     // null = 不脱敏
    String rawValue();
}
public interface Masker {                    // SPI；standard4j 内置 no-op 默认实现
    String mask(String rawValue, MaskRule rule);
    boolean supports(MaskRule rule);
}
```

- 规则元数据（保留位数/掩码位数）定义在 `MaskRule` 上，沿用旧 `IdCardType.sensitiveLeft/sensitiveRight` 模式；
- 掩码字符默认 `*`；
- 旧算法参考：身份证 6/4、手机号 3/4、中文姓名按长度（2 字掩 1，3 字以上首尾各留 1）。

## 11. 从 commons-common 的借鉴清单

借鉴（设计）：`ResCode`、`BusinessException`/`SystemException`/`UnLoginException`/`SystemResCode`、`IdCardType`（含脱敏元数据）、`CountryOrDict`、民族字典、`SensitiveUtils` 算法、pom 依赖坐标（commons-lang3/collections4/io、Jackson 3 `tools.jackson.*`、jakarta、Lombok）。

修正：
1. `Gender` 码值/名称对调 bug → 按 GB/T 2261.1 重定；
2. `Nationality` 实为民族 → 拆为 `EthnicGroup`（民族）与国家/国籍两个标准；
3. `ResCode` 机翻 javadoc → 全部重写；
4. Spring Boot 强耦合（spring.factories/自动装配）→ 不迁移，standard4j 保持零框架依赖。

不迁移：`App/CommonBean`、`event`、`MamsSession*`、`CacheNameAndTTL*`（运行时/框架能力，非本库职责）。

## 12. 待定项

- [ ] 统一响应体 `Result` 与分页模型的归属（standard4j 或 util4j）；
- [ ] 证件类型代码：主码 GA/T 2000.156-2016（无 GB 国标），需持标准全文核对旧码值；
- [ ] 平台标准 / 平台应用标准的具体定义（待需求方澄清）；
- [ ] 日期/时间表述约定（存储时区、年龄周岁计算规范）——候选，未纳入范围。
