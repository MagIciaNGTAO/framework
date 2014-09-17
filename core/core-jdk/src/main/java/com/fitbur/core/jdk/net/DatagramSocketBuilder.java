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

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class DatagramSocketBuilder {

    public SocketBuilder socket(SocketAddress address) {
        return new SocketBuilder(address);
    }

    public InetBuilder inet(int port) {
        return new InetBuilder(port);
    }

    public DatagramSocket build() throws SocketException {
        return new DatagramSocket();
    }

    public class SocketBuilder {

        private final SocketAddress address;

        private SocketBuilder(SocketAddress address) {
            this.address = address;
        }

        public DatagramSocket build() throws SocketException {
            return new DatagramSocket(address);
        }

    }

    public class InetBuilder {

        private InetAddress address;
        private final Integer port;

        private InetBuilder(Integer port) {
            this.port = port;
        }

        public InetBuilder address(InetAddress address) {
            this.address = address;

            return this;
        }

        public DatagramSocket build() throws SocketException {
            return new DatagramSocket(port, address);
        }

    }

}
