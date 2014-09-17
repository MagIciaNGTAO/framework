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

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class StringsNGTest {

    Strings sut;
    Optionals optionals;

    @BeforeMethod
    public void init() {
        optionals = new Optionals();
        sut = new Strings(optionals);
    }

    @DataProvider
    Object[][] invalidIsEmptyData() {
        return new Object[][]{
            {null},
            {""}
        };
    }

    @DataProvider
    Object[][] invalidIsBlankData() {
        return new Object[][]{
            {null},
            {""},
            {" "},
            {"\t"},
            {"\r"},
            {"\r\n"}
        };
    }

    @Test(dataProvider = "invalidIsEmptyData")
    public void givenInvalidDataIsEmptyShouldReturnTrue(String string) {
        boolean result = sut.isEmpty(string);
        assertThat(result).isTrue();
    }

    @Test(dataProvider = "invalidIsBlankData")
    public void givenInvalidDataIsBlankShouldReturnTrue(String string) {
        boolean result = sut.isBlank(string);
        assertThat(result).isTrue();
    }

    @Test
    public void givenStringIsEmptyShouldReturnFalse() {
        boolean result = sut.isEmpty("test");
        assertThat(result).isFalse();
    }

    @Test
    public void givenStringIsBlankShouldReturnFalse() {
        boolean result = sut.isBlank("test");
        assertThat(result).isFalse();
    }

    @Test
    public void givenEmptyStringOfEmptyShouldReturnEmpty() {
        String string = "";
        Optional<String> result = sut.ofEmpty(string);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenValidStringOfEmptyShouldReturnEmpty() {
        String string = "test";
        Optional<String> result = sut.ofEmpty(string);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(string);
    }

    @Test
    public void givenNullStringOfBlankShouldReturnEmpty() {
        String string = null;
        Optional<String> result = sut.ofBlank(string);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenEmptyStringOfBlankShouldReturnEmpty() {
        String string = "";
        Optional<String> result = sut.ofBlank(string);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenBlankStringOfBlankShouldReturnEmpty() {
        String string = "  ";
        Optional<String> result = sut.ofBlank(string);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenValidStringOfBlankShouldReturnEmpty() {
        String string = "test";
        Optional<String> result = sut.ofBlank(string);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(string);
    }

}
