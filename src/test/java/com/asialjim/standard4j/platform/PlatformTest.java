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

package com.asialjim.standard4j.platform;

import com.asialjim.standard4j.code.Codes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 平台契约与平台应用仓库契约测试。
 */
class PlatformTest {

    enum SamplePlatform implements Platform {
        UNKNOWN("-", "未知平台"),
        WEB("WEB", "官网"),
        MINI_PROGRAM("MINI", "小程序"),
        ;

        private final String code;
        private final String label;

        SamplePlatform(String code, String label) {
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

    enum SampleApplication implements PlatformApplication {
        UNKNOWN("-", "未知应用", SamplePlatform.UNKNOWN),
        OFFICIAL_SITE("official-site", "官网应用", SamplePlatform.WEB),
        MINI_MALL("mini-mall", "小程序商城", SamplePlatform.MINI_PROGRAM),
        MINI_MEMBER("mini-member", "小程序会员", SamplePlatform.MINI_PROGRAM),
        ;

        private final String code;
        private final String label;
        private final Platform platform;

        SampleApplication(String code, String label, Platform platform) {
            this.code = code;
            this.label = label;
            this.platform = platform;
        }

        @Override
        public String code() {
            return this.code;
        }

        @Override
        public String label() {
            return this.label;
        }

        @Override
        public Platform platform() {
            return this.platform;
        }
    }

    static class SampleApplicationRepository implements PlatformApplicationRepository {
        @Override
        public PlatformApplication unknown() {
            return SampleApplication.UNKNOWN;
        }

        @Override
        public Optional<PlatformApplication> ofNullable(String code) {
            return Codes.ofNullable(SampleApplication.values(), code);
        }

        @Override
        public List<PlatformApplication> ofPlatform(String platformCode) {
            return Arrays.stream(SampleApplication.values())
                    .filter(app -> platformCode != null && platformCode.equalsIgnoreCase(app.platformCode()))
                    .map(PlatformApplication.class::cast)
                    .toList();
        }
    }

    @Test
    @DisplayName("平台应用：platform() 明确所属平台，platformCode()/belongsTo 可用")
    void applicationBelongsToPlatform() {
        PlatformApplication app = SampleApplication.MINI_MALL;
        assertAll(
                () -> assertEquals(SamplePlatform.MINI_PROGRAM, app.platform()),
                () -> assertEquals("MINI", app.platformCode()),
                () -> assertTrue(app.belongsTo(SamplePlatform.MINI_PROGRAM)),
                () -> assertFalse(app.belongsTo(SamplePlatform.WEB)),
                () -> assertFalse(app.belongsTo(null))
        );
    }

    @Test
    @DisplayName("平台应用仓库：按编码三口径与按平台查询")
    void repositoryQueries() {
        PlatformApplicationRepository repository = new SampleApplicationRepository();
        assertAll(
                () -> assertEquals(SampleApplication.MINI_MALL, repository.of("mini-mall")),
                () -> assertEquals(SampleApplication.OFFICIAL_SITE, repository.of("OFFICIAL_SITE")),
                () -> assertEquals(SampleApplication.UNKNOWN, repository.ofOrDefault("nope")),
                () -> assertEquals(List.of(SampleApplication.MINI_MALL, SampleApplication.MINI_MEMBER),
                        repository.ofPlatform("MINI")),
                () -> assertEquals(List.of(SampleApplication.MINI_MALL, SampleApplication.MINI_MEMBER),
                        repository.ofPlatform(SamplePlatform.MINI_PROGRAM)),
                () -> assertEquals(List.of(), repository.ofPlatform((Platform) null))
        );
    }
}
