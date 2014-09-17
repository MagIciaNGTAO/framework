/*
 * Copyright 2014 Fitbur.
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
package com.fitbur.core.config.impl;

import com.fitbur.core.config.YamlLoader;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class YamlLoaderNGTest {

    @Inject
    YamlLoader sut;

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullResourceShouldThrowException() throws IOException {
        sut.load(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenNonExistentResourceShouldThrowException() throws IOException {
        sut.load("none.yaml");
    }

    @Test
    public void givenValidResourceShouldReturn() throws IOException {
        String resource = "test.yml";

        Map<String, Object> resut = sut.load(resource);
        assertThat(resut).isNotNull();
        assertThat(resut).containsEntry("test", "test");
    }

}
