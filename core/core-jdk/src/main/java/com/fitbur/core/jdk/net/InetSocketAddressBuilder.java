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

import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class InetSocketAddressBuilder {

    public PortBuilder port(int port) {
        return new PortBuilder(port);
    }

    public class PortBuilder {

        private final Integer port;

        private PortBuilder(Integer port) {
            this.port = port;
        }

        public InetBuilder inet(InetAddress address) {
            return new InetBuilder(address, port);
        }

        public InetSocketAddress build() {
            return new InetSocketAddress(port);
        }

    }

    public class InetBuilder {

        private final InetAddress address;
        private final Integer port;

        private InetBuilder(InetAddress address, Integer port) {
            this.address = address;
            this.port = port;
        }

        public InetSocketAddress build() {
            return new InetSocketAddress(address, port);
        }

    }

    public class HostBuilder {

        private final String host;
        private final Integer port;

        private HostBuilder(String host, Integer port) {
            this.host = host;
            this.port = port;
        }

        public InetSocketAddress build() {
            return new InetSocketAddress(host, port);
        }

    }

}