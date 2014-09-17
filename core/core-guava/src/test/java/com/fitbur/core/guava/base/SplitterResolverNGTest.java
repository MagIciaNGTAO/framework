/*
 * Copyright 2014 Fitbur, Inc..
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
package com.fitbur.core.guava.base;

import com.google.common.base.Splitter;
import javax.inject.Inject;
import javax.inject.Named;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author saden
 */
@HK2
public class SplitterResolverNGTest {

    @Inject
    @Named(",")
    Splitter sut;

    @BeforeClass
    public void init() {
        assertThat(sut).isNotNull();
    }

    @Test
    public void givenCommaSeperatedStringSplitShouldReturnTwoResults() {
        Iterable<String> result = sut.split("test1,test2");
        assertThat(result).hasSize(2).contains("test1", "test2");
    }

}
