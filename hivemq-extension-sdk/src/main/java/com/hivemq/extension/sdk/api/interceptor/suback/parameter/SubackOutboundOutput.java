/*
 * Copyright 2019 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.extension.sdk.api.interceptor.suback.parameter;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.async.Async;
import com.hivemq.extension.sdk.api.async.SimpleAsyncOutput;
import com.hivemq.extension.sdk.api.interceptor.suback.SubackOutboundInterceptor;
import com.hivemq.extension.sdk.api.packets.suback.ModifiableSubackPacket;

import java.time.Duration;

/**
 * This is the output parameter of any {@link SubackOutboundInterceptor} providing methods to modify a SUBACK packet
 * before it is sent to the client.
 *
 * @author Robin Atherton
 **/
@DoNotImplement
public interface SubackOutboundOutput extends SimpleAsyncOutput<SubackOutboundOutput> {

    /**
     * Use this object to make any changes to the outbound SUBACK.
     *
     * @return A modifiable SUBACK packet.
     */
    @NotNull ModifiableSubackPacket getSubackPacket();

    /**
     * If the timeout is expired before {@link Async#resume()} is called then the outcome is handled as failed. In that
     * case an unmodified SUBACK is forwarded to the next interceptor or client, all changes made by this interceptor
     * are not passed on.
     * <p>
     * Do not call this method more than once. If an async method is called multiple times an exception is thrown.
     *
     * @param timeout Timeout that HiveMQ waits for the result of the async operation.
     * @throws UnsupportedOperationException If async is called more than once.
     */
    @Override
    @NotNull Async<SubackOutboundOutput> async(@NotNull Duration timeout);
}