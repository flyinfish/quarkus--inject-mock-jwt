package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class JwtBeanInjectMockWorkaroundTest {
    @Inject
    JwtBean bean;
    //@InjectMock
    JsonWebToken jwt;

    @BeforeEach
    void workaround() {
        jwt = mock(JsonWebToken.class);
        QuarkusMock.installMockForType(jwt, JsonWebToken.class);
    }


    @Test
    void shouldReturnName() {
        when(jwt.getName()).thenReturn("--test--");

        var actual = bean.getName();
        
        assertEquals("--test--", actual);
    }
}
