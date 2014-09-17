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

import java.io.IOException;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class MulticastSocketBuilder {

    public PortBuilder bind(int port) {
        return new PortBuilder(port);
    }

    public AddressBuilder bind(SocketAddress address) {
        return new AddressBuilder(address);
    }

    public MulticastSocket build() throws IOException {
        return new MulticastSocket();
    }

    public class PortBuilder {

        private final Integer port;

        private PortBuilder(Integer port) {
            this.port = port;
        }

        public MulticastSocket build() throws IOException {
            return new MulticastSocket(port);
        }
    }

    public class AddressBuilder {

        private final SocketAddress address;

        private AddressBuilder(SocketAddress address) {
            this.address = address;
        }

        public MulticastSocket build() throws IOException {
            return new MulticastSocket(address);
        }

    }

}
