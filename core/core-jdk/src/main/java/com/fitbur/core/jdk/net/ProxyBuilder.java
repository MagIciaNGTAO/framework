/*
 * Copyright 2014 Sharmarke Aden (saden1).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fitbur.core.jdk.net;

import java.net.Proxy;
import java.net.SocketAddress;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class ProxyBuilder {

    private Proxy.Type type;
    private SocketAddress address;

    @EnsuresNonNull("type")
    public ProxyBuilder type(Proxy.Type type) {
        this.type = type;

        return this;
    }

    @EnsuresNonNull("address")
    public ProxyBuilder address(SocketAddress address) {
        this.address = address;

        return this;
    }

    @RequiresNonNull({"type", "address"})
    public Proxy build() {
        return new Proxy(type, address);
    }

}
