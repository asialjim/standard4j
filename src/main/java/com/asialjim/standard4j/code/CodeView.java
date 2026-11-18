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

package com.asialjim.standard4j.code;

import java.io.Serializable;
import java.util.Objects;

/**
 * 字典项结构化视图 —— 给前端的展示报文使用：
 * 前端用 {@link #label()} 做阅读友好渲染，用 {@link #code()} 回传，防止断链。
 *
 * <p>不可变值对象；序列化输出形如 {@code {"code":"1","label":"男"}}。</p>
 *
 * @param code        标准编码
 * @param label       展示名
 * @param description 描述说明
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public record CodeView(String code, String label, String description) implements Serializable {

    public CodeView {
        Objects.requireNonNull(code, "code 不能为空");
        Objects.requireNonNull(label, "label 不能为空");
    }

    /**
     * 由字典项构建视图
     *
     * @param code {@link Code 字典项}
     * @return {@link CodeView}；入参为 {@code null} 时返回 {@code null}
     */
    public static CodeView of(Code code) {
        if (code == null)
            return null;
        return new CodeView(code.code(), code.label(), code.description());
    }
}
