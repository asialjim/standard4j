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

对齐映射（旧 commons-common → 新标准）：

| 字典 | 旧实现 | 新标准依据 |
|---|---|---|
| 性别 `Gender` | 自定义 `0=女 1=男`（**有 bug：码值与名称对调**） | **GB/T 2261.1**：`0`=未知、`1`=男、`2`=女、`9`=未说明 |
| 民族 `Nationality`（命名误导，实为民族） | 自定义三字码 `HAN` 等 | **GB/T 3304** 2 位数字码（01 汉族…），自定义三字码可作为辅助别名保留 |
| 国家/地区 `CountryOrDict` | 电话区号当主键 `0086` + alpha-3 | **ISO 3166-1**：alpha-2 为主键，另存 alpha-3、数字码、电话区号、中英文名 |
| 证件类型 `IdCardType` | 自定义 `"01".."13","99"` | **无 GB 国标覆盖证件类型代码**。主码采用 **GA/T 2000.156-2016**《公安信息代码 第156部分：常用证件代码》（覆盖面最全、与旧码表一致，待持全文核对码值）；内置 **JR/T 0160-2018**（金融 DM001）别名码；预留 `certificateTypeCode` 关联电子证照目录代码（可空）。每项自带脱敏保留位数 |
| 证照类型 `certificate` | 无 | **GB/T 36901~36904-2018 电子证照系列为准**（目录代码、证照标识规则）。开放目录、数据驱动（内嵌常用项 + 目录加载/注册），不做封闭枚举 |

## 1bis. 证照类型标准（`certificate`）

**以 GB/T 36901~36904-2018（电子证照系列国标）为准**：

- `CertificateType`：目录代码、证照名称、发证机构、发证日期/有效期元数据；证照标识按 **GB/T 36904-2018** 规则生成（OID 形式，如 `1.2.156.3005.2.x`）。
- **数据策略（已定）：常用证照做封闭枚举，冷门证照走文件数据驱动**：
  - 枚举内置高频证照项（营业执照、居民户口簿、结婚证、学历证书等）；
  - 冷门/行业专属证照由目录文件补充：默认内置 classpath JSON（GB/T 36901 目录代码），使用方可注册自定义目录文件；
  - 统一经 Facade 查找：`of(code)` 先查枚举、再查文件目录，别名解析同理，对外无感知。
- **该模式为全库通用**：小字典（性别、民族、证件类型等）纯封闭枚举；大数据集（行政区划 GB/T 2260 市/县级、证照目录）常用项枚举 + 文件驱动兜底。
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

只定义规范接口/抽象类（"这类实体应有的字段"），不含实现：

- **自然人**：姓名（姓/名/曾用名）、性别、出生日期、民族、婚姻状况……
- **证件**：证件类型、证件号码、证件名称、发证机关、有效期起止。
- **联系方式**：手机号、电话、邮箱；**地址**：省/市/区县/详址（与 GB/T 2260 联动）。
- **组织/法人**：名称、统一社会信用代码、法定代表人。

边界：校验规则的定义归 standard4j；脱敏处理归 sensitive4j。

## 4. 区域标准（`region`）

- 国家/地区：ISO 3166-1 alpha-2（主键）+ alpha-3 + 数字码 + 电话区号 + 中英文名。
- 行政区划：GB/T 2260 省市区县（6 位）、GB/T 10114 乡镇级；**数据逐年变化，需版本化更新机制**（内嵌枚举 + 后续数据源接口）。

## 5. 国际化（`i18n`）

- 语言 ISO 639-1、货币 ISO 4217、时区 IANA、Locale 约定。

## 6. 脱敏规则接口（`sensitive`）

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

## 7. 从 commons-common 的借鉴清单

借鉴（设计）：`ResCode`、`BusinessException`/`SystemException`/`UnLoginException`/`SystemResCode`、`IdCardType`（含脱敏元数据）、`CountryOrDict`、民族字典、`SensitiveUtils` 算法、pom 依赖坐标（commons-lang3/collections4/io、Jackson 3 `tools.jackson.*`、jakarta、Lombok）。

修正：
1. `Gender` 码值/名称对调 bug → 按 GB/T 2261.1 重定；
2. `Nationality` 实为民族 → 拆为 `EthnicGroup`（民族）与国家/国籍两个标准；
3. `ResCode` 机翻 javadoc → 全部重写；
4. Spring Boot 强耦合（spring.factories/自动装配）→ 不迁移，standard4j 保持零框架依赖。

不迁移：`App/CommonBean`、`event`、`MamsSession*`、`CacheNameAndTTL*`（属 util4j/cache4j/web4j 职责）。

## 8. 待定项

- [ ] 统一响应体 `Result` 与分页模型的归属（standard4j vs util4j）；
- [x] ~~代码表数据维护~~ **已定**：常用项封闭枚举 + 冷门项文件数据驱动（classpath JSON，可注册自定义文件）；小字典纯枚举，大数据集（GB/T 2260 市/县级、证照目录）枚举 + 文件兜底；
- [ ] 证件类型代码：主码 GA/T 2000.156-2016（无 GB 国标），需持标准全文核对旧码值；JR/T 0160-2018 DM001 别名码内置；其他行业标准经 AliasTable SPI 扩展；公民身份号码校验（GB 11643-1999）单独实现；
- [ ] 平台标准 / 平台应用标准的具体定义（待需求方澄清）。
