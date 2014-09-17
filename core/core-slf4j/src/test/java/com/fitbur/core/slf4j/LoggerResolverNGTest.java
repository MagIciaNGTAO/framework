package com.fitbur.core.slf4j;

import com.fitbur.core.slf4j.fixture.assisted.AssistedInjectionService;
import com.fitbur.core.slf4j.fixture.assisted.NamedAssistedInjectionService;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jvnet.testing.hk2testng.HK2;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
@HK2
public class LoggerResolverNGTest {

    @Inject
    AssistedInjectionService assistedInjectionService;
    @Inject
    NamedAssistedInjectionService namedAssistedInjectionService;

    @Test
    public void assertInjections() {
        assertThat(assistedInjectionService).isNotNull();
        assertThat(assistedInjectionService.getField()).isNotNull();
        assertThat(assistedInjectionService.getConstructor()).isNotNull();
        assertThat(assistedInjectionService.getMethod()).isNotNull();
    }

    @Test
    public void assertNamedLoggerTestServiceInjection() {
        assertThat(namedAssistedInjectionService.getField()).isNotNull();
        assertThat(namedAssistedInjectionService.getConstructor()).isNotNull();
        assertThat(namedAssistedInjectionService.getMethod()).isNotNull();
        assertThat(namedAssistedInjectionService.getField().getName()).isEqualTo("field");
        assertThat(namedAssistedInjectionService.getConstructor().getName()).isEqualTo("constructor");
        assertThat(namedAssistedInjectionService.getMethod().getName()).isEqualTo("method");
    }
}
