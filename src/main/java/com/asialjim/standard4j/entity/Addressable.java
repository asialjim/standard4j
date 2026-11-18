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

package com.asialjim.standard4j.entity;

/**
 * 地址字段契约 —— 省/市/区县三级**行政区划代码** + 详细地址。
 *
 * <p>三级代码遵循 <b>GB/T 2260</b>（6 位：前 2 位省级、中 2 位市级、后 2 位县级），
 * 本库不内置区划数据，代码的合法性由使用方的
 * {@link com.asialjim.standard4j.region.AdministrativeDivisionRepository} 校验。</p>
 *
 * <p>字段以字符串承载码值而非枚举：区划数据逐年变更，码值类型化会让老数据无法表达。</p>
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface Addressable {

    /**
     * 省级行政区划代码（GB/T 2260 前 2 位 + 补零，如 {@code "440000"}）
     *
     * @return 省级代码
     */
    String getProvinceCode();

    /**
     * 设置省级行政区划代码
     *
     * @param provinceCode 省级代码
     */
    void setProvinceCode(String provinceCode);

    /**
     * 市级行政区划代码（如 {@code "440300"}）
     *
     * @return 市级代码
     */
    String getCityCode();

    /**
     * 设置市级行政区划代码
     *
     * @param cityCode 市级代码
     */
    void setCityCode(String cityCode);

    /**
     * 区县级行政区划代码（如 {@code "440304"}）
     *
     * @return 区县级代码
     */
    String getDistrictCode();

    /**
     * 设置区县级行政区划代码
     *
     * @param districtCode 区县级代码
     */
    void setDistrictCode(String districtCode);

    /**
     * 详细地址（街道、门牌号等）
     *
     * @return 详细地址
     */
    String getDetailAddress();

    /**
     * 设置详细地址
     *
     * @param detailAddress 详细地址
     */
    void setDetailAddress(String detailAddress);
}
