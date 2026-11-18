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

import com.asialjim.standard4j.code.Code;
import com.asialjim.standard4j.human.EthnicGroup;
import com.asialjim.standard4j.human.Gender;
import com.asialjim.standard4j.human.IdCardType;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 命名标准（{@code entity}）契约测试：接口组合、周岁推导、证件有效期判定。
 */
class EntityStandardTest {

    /** 业务方自定义生命周期状态（本库不内置状态字典）。 */
    enum MemberState implements Code {
        PENDING("0", "待生效"),
        ACTIVE("1", "生效中"),
        ;

        private final String code;
        private final String label;

        MemberState(String code, String label) {
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
    }

    /** 组合示例：一个自然人实体按需实现 11 个命名接口（无任何继承）。 */
    @Getter
    @Setter
    static class Member implements Nameable, Genderable, BirthDateable, Ethnicable, Certifiable,
            Contactable, Addressable, Auditable, Versionable, SoftDeletable, LifeCycle<MemberState> {

        private String name;
        private String formerName;
        private Gender gender;
        private LocalDate birthDate;
        private EthnicGroup ethnicGroup;
        private IdCardType certificateType;
        private String certificateNo;
        private String certificateName;
        private String certificateIssuer;
        private LocalDate certificateValidFrom;
        private LocalDate certificateValidTo;
        private String mobile;
        private String telephone;
        private String email;
        private String provinceCode;
        private String cityCode;
        private String districtCode;
        private String detailAddress;
        private String createdBy;
        private LocalDateTime createdAt;
        private String updatedBy;
        private LocalDateTime updatedAt;
        private Long version;
        private Boolean deleted;
        private MemberState state;
    }

    private static Member member() {
        Member m = new Member();
        m.setName("张三");
        m.setFormerName("张小三");
        m.setGender(Gender.MALE);
        m.setBirthDate(LocalDate.of(2000, 6, 15));
        m.setEthnicGroup(EthnicGroup.HAN);
        m.setCertificateType(IdCardType.RESIDENT_ID_CARD);
        m.setCertificateNo("440304200006150012");
        m.setProvinceCode("440000");
        m.setCityCode("440300");
        m.setDistrictCode("440304");
        m.setDetailAddress("某某街道 1 号");
        m.setState(MemberState.ACTIVE);
        return m;
    }

    @Test
    @DisplayName("接口可组合：一个实体实现任意多个命名接口，无继承耦合")
    void composition() {
        Member m = member();
        assertAll(
                () -> assertInstanceOf(Nameable.class, m),
                () -> assertInstanceOf(Genderable.class, m),
                () -> assertInstanceOf(BirthDateable.class, m),
                () -> assertInstanceOf(Certifiable.class, m),
                () -> assertInstanceOf(Addressable.class, m),
                () -> assertEquals("张三", m.getName()),
                () -> assertEquals(Gender.MALE, m.getGender()),
                () -> assertEquals(IdCardType.RESIDENT_ID_CARD, m.getCertificateType()),
                () -> assertEquals(MemberState.ACTIVE, m.getState())
        );
    }

    @Test
    @DisplayName("周岁推导：未过生日不加岁、2 月 29 日平年按 2 月 28 日")
    void age() {
        Member m = member();
        assertAll(
                () -> assertEquals(24, m.ageOn(LocalDate.of(2025, 6, 14)).getAsInt()),
                () -> assertEquals(25, m.ageOn(LocalDate.of(2025, 6, 15)).getAsInt()),
                () -> assertTrue(m.age().isPresent()),
                () -> {
                    Member leap = new Member();
                    leap.setBirthDate(LocalDate.of(2000, 2, 29));
                    assertEquals(24, leap.ageOn(LocalDate.of(2025, 2, 27)).getAsInt());
                    assertEquals(25, leap.ageOn(LocalDate.of(2025, 2, 28)).getAsInt());
                },
                () -> assertTrue(new Member().age().isEmpty())
        );
    }

    @Test
    @DisplayName("证件有效期：起止日均有效，validTo 为空视为长期有效")
    void certificateValidity() {
        Member m = member();
        m.setCertificateValidFrom(LocalDate.of(2020, 1, 1));
        m.setCertificateValidTo(LocalDate.of(2030, 12, 31));
        assertAll(
                () -> assertTrue(m.certificateValidOn(LocalDate.of(2020, 1, 1))),
                () -> assertTrue(m.certificateValidOn(LocalDate.of(2025, 6, 15))),
                () -> assertTrue(m.certificateValidOn(LocalDate.of(2030, 12, 31))),
                () -> assertFalse(m.certificateValidOn(LocalDate.of(2031, 1, 1))),
                () -> assertFalse(m.certificateValidOn(LocalDate.of(2019, 12, 31))),
                () -> {
                    m.setCertificateValidTo(null);
                    assertTrue(m.certificateValidOn(LocalDate.of(2099, 1, 1)));
                },
                () -> assertFalse(m.certificateValidOn(null))
        );
    }
}
