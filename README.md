# standard4j

以 Java 语言定义的**开发标准库**：不是语言标准库，而是把业务开发中反复出现的"标准"沉淀为可复用的代码——行政代码、国籍/民族代码、平台标准、业务异常体系、命名标准（实体字段规范）、脱敏规则接口等。

## 标准清单

| 包 | 内容 | 对齐标准 |
|---|---|---|
| `code` | 统一字典模型（code/name/status/sort/层级）与查找约定；通用业务字典 | 自定模型，字典项对齐各自国标 |
| `exception` | 业务异常体系、统一响应码接口（`ResCode`）、错误码规范 | 自定 |
| `entity` | 命名标准：自然人、证件、联系方式、地址、组织的规范字段集 | 字段语义对齐国标 |
| `region` | 国家/地区、行政区划 | ISO 3166-1（alpha-2/alpha-3/数字码）、GB/T 2260 |
| `i18n` | 语言、货币、时区、Locale | ISO 639-1、ISO 4217、IANA tzdb |
| `human` | 性别、民族、证件类型、国籍等人本字典 | GB/T 2261.1（性别）、GB/T 3304（民族）等 |
| `platform` | 平台标准 / 平台应用标准 | 待定义 |
| `sensitive` | 脱敏规则标识（`MaskRule`）、声明接口（`Maskable`）、执行 SPI（`Masker`） | 自定；实现在 sensitive4j |

## 对齐原则

- **能与国标对齐的与国标对齐（GB 系列优先）；能与 ISO 对齐的与 ISO 对齐。**
- 国标/ISO 覆盖不到的（如平台标准、错误码），才使用自定义编码，并需在 Javadoc 中注明出处。
- 字典项的 Javadoc 必须写明依据的标准号（如 GB/T 2261.1）。

## 设计要点

详见 [docs/DESIGN.md](docs/DESIGN.md)。

## 依赖定位

- `standard4j` 是本工作区（cache4j / util4j / sensitive4j / web4j）的**公共底座**：其他库可依赖它，它不反向依赖任何兄弟库。
- 纯模型 + 接口库：**不依赖 Spring / 任何运行时框架**；框架集成（自动装配等）由使用方或 web4j 完成。
- 构建：Maven；groupId / 包前缀 `com.asialjim.standard4j`。
