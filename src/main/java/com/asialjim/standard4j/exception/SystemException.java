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

package com.asialjim.standard4j.exception;

/**
 * 系统异常 —— 系统内部错误，不应向用户暴露细节。
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public class SystemException extends BusinessException {

    public SystemException(ResCode resCode) {
        super(resCode);
    }

    public SystemException(ResCode resCode, String message) {
        super(resCode, message);
    }

    public SystemException(ResCode resCode, String message, Throwable cause) {
        super(resCode, message, cause);
    }
}
