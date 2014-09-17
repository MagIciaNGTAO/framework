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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class OptionalsNGTest {

    Optionals sut;

    @BeforeMethod
    public void init() {
        sut = new Optionals();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullOfShouldThrowException() {
        sut.of(null);
    }

    @Test
    public void givenValueOfShouldReturnValue() {
        Optional<String> result = sut.of("");
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void givenNullOfNullableShouldReturnEmpty() {
        Optional<String> result = sut.ofNullable(null);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenValueOfNullableShouldReturnValue() {
        Optional<String> result = sut.ofNullable("");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEmpty();
    }

    @Test
    public void givenNothingEmptyShouldReturnEmpty() {
        Optional<String> result = sut.empty();
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenFalseExprToEmptyShouldReturnEmpty() {
        Optional<String> result = sut.toEmpty(false, "");
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenTrueExprToEmptyShouldReturnValue() {
        Optional<String> result = sut.toEmpty(true, "");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEmpty();
    }

    @Test
    public void givenNullOfEmptyMapShouldReturnEmpty() {
        Map<String, String> map = null;
        Optional<Map<String, String>> result = sut.ofEmpty(map);
        assertThat(result.isPresent()).isFalse();

    }

    @Test
    public void givenEmptyMapOfEmptyMapShouldReturnEmpty() {
        Map<String, String> map = new HashMap<>();
        Optional<Map<String, String>> result = sut.ofEmpty(map);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenValidMapOfEmptyMapShouldReturnEmpty() {
        Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        Optional<Map<String, String>> result = sut.ofEmpty(map);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).hasSize(1).containsEntry("test", "test");
    }

    @Test
    public void givenNullOfEmptyPropertiesShouldReturnEmpty() {
        Optional<Properties> result = sut.ofEmpty((Properties) null);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenEmptyPropertiesOfEmptyMapShouldReturnEmpty() {
        Optional<Properties> result = sut.ofEmpty(new Properties());
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenValidPropertiesOfEmptyMapShouldReturnEmpty() {
        Properties properties = new Properties();
        properties.put("test", "test");
        Optional<Properties> result = sut.ofEmpty(properties);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).hasSize(1).containsEntry("test", "test");
    }

    @Test
    public void givenNullOfEmptyCollectionShouldReturnEmpty() {
        List<String> list = new ArrayList<>();
        Optional<List<String>> result = sut.ofEmpty(list);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenEmptyListOfEmptyCollectionShouldReturnEmpty() {
        List<String> list = null;
        Optional<List<String>> result = sut.ofEmpty(list);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenValidListOfEmptyCollectionShouldReturnEmpty() {
        List<String> list = new ArrayList<>();
        list.add("test");
        Optional<List<String>> result = sut.ofEmpty(list);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).contains("test");
    }

}
