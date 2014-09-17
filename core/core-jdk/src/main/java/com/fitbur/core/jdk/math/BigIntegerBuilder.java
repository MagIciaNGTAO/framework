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
package com.fitbur.core.jdk.math;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Sharmarke Aden
 */
@PerLookup
@Service
public class BigIntegerBuilder {

    public static final Integer DEFAULT_RADIX = 10;

    public NumBitsBuilder numBits(int numBits) {
        return new NumBitsBuilder(numBits);
    }

    public BitLengthBuilder bitLength(int bitLength, int certainty) {
        return new BitLengthBuilder(bitLength, certainty);
    }

    public StringBuilder value(String value) {
        return new StringBuilder(value);
    }

    public static abstract class RandomBuilder<T> {

        private static final Random DEFAULT_RANDOM = new SecureRandom();
        protected Random random = DEFAULT_RANDOM;

        public T random(Random random) {
            this.random = random;

            return (T) this;
        }
    }

    public class NumBitsBuilder extends RandomBuilder<NumBitsBuilder> {

        private final Integer numBits;

        private NumBitsBuilder(Integer numBits) {
            this.numBits = numBits;
        }

        public BigInteger build() {
            return new BigInteger(numBits, random);
        }
    }

    public class BitLengthBuilder extends RandomBuilder<BitLengthBuilder> {

        private final Integer bitLength;
        private final Integer certainty;

        private BitLengthBuilder(Integer bitLength, Integer certainty) {
            this.bitLength = bitLength;
            this.certainty = certainty;
        }

        public BigInteger build() {
            return new BigInteger(bitLength, certainty, random);
        }
    }

    public class StringBuilder {

        private final String value;
        private Integer radix = DEFAULT_RADIX;

        private StringBuilder(String value) {
            this.value = value;
        }

        public StringBuilder radix(int radix) {
            this.radix = radix;

            return this;
        }

        public BigInteger build() {
            return new BigInteger(value, radix);
        }

    }
}
