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

/**
 * 生命周期状态字段契约 —— <b>状态字典由业务方定义</b>（本库不内置，通用状态在不同业务域语义差异过大）。
 *
 * <p>用法：业务方定义实现 {@link Code} 的状态枚举，再把实体绑到该枚举上，
 * 状态码值合法性由类型系统保证：</p>
 * <pre>{@code
 * public enum OrderState implements Code {
 *     DRAFT("0", "草稿"), PAID("1", "已支付"), ...
 * }
 *
 * public class Order implements LifeCycle<OrderState> {
 *     private OrderState state;
 *     public OrderState getState() { return state; }
 *     public void setState(OrderState state) { this.state = state; }
 * }
 * }</pre>
 *
 * @param <S> 业务方定义的生命周期状态类型
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @since 1.0.0
 */
public interface LifeCycle<S extends Code> {

    /**
     * 当前生命周期状态
     *
     * @return 状态
     */
    S getState();

    /**
     * 设置生命周期状态
     *
     * @param state 状态
     */
    void setState(S state);
}
