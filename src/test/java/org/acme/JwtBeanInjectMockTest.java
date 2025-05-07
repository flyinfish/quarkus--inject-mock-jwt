package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

@QuarkusTest
@ActivateRequestContext // just an attempt to make it work
public class JwtBeanInjectMockTest {
    @Inject
    JwtBean bean;
    @InjectMock
    JsonWebToken jwt;

    @Test
    void shouldReturnName() {
        when(jwt.getName()).thenReturn("--test--");

        var actual = bean.getName();
        
        assertEquals("--test--", actual);
    }
}
