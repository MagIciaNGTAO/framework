/*
 * Copyright 2014 Sharmarke Aden.
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
package com.fitbur.test.hk2;

import com.fitbur.test.hk2.fixture.PrimeryService;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class SharedLocatorNGTest extends SharedLocator {

    private PrimeryService service1;
    private PrimeryService service2;

    @AfterClass
    public void assertDifffrent() {
        assertThat(service1).isSameAs(service2);
    }

    public class Shared1NGTest extends SharedLocator {

        @Test
        public void assertService() {
            service1 = locator.getService(PrimeryService.class);
            assertThat(service1).isNotNull();
        }

    }

    public class Shared2NGTest extends SharedLocator {

        @Test
        public void assertService() {
            service2 = locator.getService(PrimeryService.class);
            assertThat(service2).isNotNull();
        }

    }

}
