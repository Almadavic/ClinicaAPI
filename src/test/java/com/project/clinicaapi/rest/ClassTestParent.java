package com.project.clinicaapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clinicaapi.config.securityConfig.LoginData;
import com.project.clinicaapi.service.serviceAction.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
public abstract class ClassTestParent {

    protected final int forbidden = 403;

    protected final int unauthorized = 401;

    protected final int badRequest = 400;

    protected final int ok = 200;

    protected final int internalServerError = 500;

    protected final int notFound = 404;

    protected final int created = 201;

    protected final int noContent = 204;

    protected final int methodNotAllowed = 405;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    protected String token(String login, String password) {

        UsernamePasswordAuthenticationToken loginAuthenticationToken = new LoginData(login, password).toConvert();

        Authentication authentication = authManager.authenticate(loginAuthenticationToken);

        String token = tokenService.generateToken(authentication);

        return "Bearer " + token;

    }

}
