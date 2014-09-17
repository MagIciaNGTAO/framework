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
package com.fitbur.core.http;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class HttpRequestFactoryProviderNGTest {

    HttpRequestFactoryProvider sut;
    NetHttpTransport transport;

    @BeforeMethod
    public void init() {
        transport = new NetHttpTransport();
        sut = new HttpRequestFactoryProvider(transport);
    }

    @Test
    public void provideShouldReturn() {
        HttpRequestFactory result = sut.provide();
        assertThat(result).isNotNull();
    }
}
