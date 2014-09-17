/*
 * Copyright 2014 saden.
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
package com.fitbur.core.util;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class StreamsNGTest {

    Streams sut;
    Optionals optionals;

    @BeforeMethod
    public void init() {
        optionals = new Optionals();
        sut = new Streams(optionals);
    }

    @Test
    public void givenNothingEmptyShouldReturnEmpty() {
        Stream result = sut.empty();
        assertThat(result.count()).isZero();
    }

    @Test
    public void givenNullOfShouldReturnEmptyStream() {
        Stream<String> result = sut.of((String) null);
        assertThat(result.count()).isZero();
    }

    @Test
    public void givenStringOfShouldShouldReturnStream() {
        String string = "test";
        Stream<String> result = sut.of(string);
        assertThat(result.count()).isEqualTo(1);
    }

    @Test
    public void givenNullArrayOfShouldReturnEmptyStream() {
        String[] strings = null;
        Stream<String> result = sut.of(strings);
        assertThat(result.count()).isZero();
    }

    @Test
    public void givenStringArrayOfShouldShouldReturnStream() {
        String[] strings = {"test", "test2"};
        Stream<String> result = sut.of(strings);
        assertThat(result.count()).isEqualTo(2);
    }

}
