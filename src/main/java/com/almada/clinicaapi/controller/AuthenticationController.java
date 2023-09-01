package com.almada.clinicaapi.controller;


import com.almada.clinicaapi.config.securityConfig.LoginData;
import com.almada.clinicaapi.config.securityConfig.Token;
import com.almada.clinicaapi.config.swaggerConfig.endPoint.AuthenticationSwagger;
import com.almada.clinicaapi.service.serviceAction.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationSwagger {

    private final AuthenticationService authService;

    @Override
    @PostMapping
    public ResponseEntity<Token> authenticate(@RequestBody @Valid LoginData loginData) {
        return ResponseEntity.ok().body(authService.authenticate(loginData));
    }

}

