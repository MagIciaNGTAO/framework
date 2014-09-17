/*
 * Copyright 2013 saden.
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

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
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
public class DatagramPacketBuilder {

    public static final Integer DEFAULT_OFFSET = 0;

    public BufferBuilder buffer() {
        return new BufferBuilder();
    }

    public SocketBuilder socket(SocketAddress address) {
        return new SocketBuilder(address);
    }

    public InetBuilder inet(InetAddress address, int port) {
        return new InetBuilder(address, port);
    }

    public class BufferBuilder {

        private byte[] buffer;
        private Integer offset = DEFAULT_OFFSET;
        private Integer length;

        @EnsuresNonNull("buffer")
        public BufferBuilder buffer(byte[] buffer) {
            this.buffer = buffer;

            return this;
        }

        @EnsuresNonNull("length")
        public BufferBuilder length(int length) {
            this.length = length;

            return this;
        }

        public BufferBuilder offset(int offset) {
            this.offset = offset;

            return this;
        }

        @RequiresNonNull({"buffer", "length"})
        public DatagramPacket build() throws Exception {
            return new DatagramPacket(buffer, offset, length);
        }
    }

    public class InetBuilder {

        private byte[] buffer;
        private Integer offset = DEFAULT_OFFSET;
        private Integer length;
        private final InetAddress address;
        private final Integer port;

        private InetBuilder(InetAddress address, Integer port) {
            this.address = address;
            this.port = port;
        }

        @EnsuresNonNull("buffer")
        public InetBuilder buffer(byte[] buffer) {
            this.buffer = buffer;

            return this;
        }

        @EnsuresNonNull("length")
        public InetBuilder length(int length) {
            this.length = length;

            return this;
        }

        public InetBuilder offset(int offset) {
            this.offset = offset;

            return this;
        }

        @RequiresNonNull({"buffer", "length"})
        public DatagramPacket build() throws Exception {
            return new DatagramPacket(buffer, offset, length, address, port);
        }
    }

    public class SocketBuilder {

        private byte[] buffer;
        private Integer offset = DEFAULT_OFFSET;
        private Integer length;

        private final SocketAddress address;

        private SocketBuilder(SocketAddress address) {
            this.address = address;
        }

        @EnsuresNonNull("buffer")
        public SocketBuilder buffer(byte[] buffer) {
            this.buffer = buffer;

            return this;
        }

        @EnsuresNonNull("length")
        public SocketBuilder length(int length) {
            this.length = length;

            return this;
        }

        public SocketBuilder offset(int offset) {
            this.offset = offset;

            return this;
        }

        @RequiresNonNull({"buffer", "length"})
        public DatagramPacket build() throws SocketException {
            return new DatagramPacket(buffer, offset, length, address);
        }
    }
}
