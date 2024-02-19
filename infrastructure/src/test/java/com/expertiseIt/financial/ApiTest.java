package com.expertiseIt.financial;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

public interface ApiTest {

    SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor CATEGORIES_JWT =
            jwt().authorities(new SimpleGrantedAuthority("ROLE_CATALOGO_CATEGORIES"));

    SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor ADMIN_JWT =
        jwt().authorities(new SimpleGrantedAuthority("ROLE_CATALOGO_ADMIN"));
}