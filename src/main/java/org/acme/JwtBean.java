package org.acme;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JwtBean {
    @Inject
    JsonWebToken jwt;

    
    public String getName() {
        return jwt.getName();
    }
    
}
