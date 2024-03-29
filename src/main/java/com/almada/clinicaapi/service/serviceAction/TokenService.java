package com.almada.clinicaapi.service.serviceAction;

import com.almada.clinicaapi.dto.response.UserMonitoringResponseDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.mapper.UserMapper;
import com.almada.clinicaapi.service.customException.JWTException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    private final UserMapper mapper;

    public String generateToken(Authentication authentication) {

        UserMonitoringResponseDTO userDTO = mapper.toUserMonitoringDTO((User) authentication.getPrincipal());

        try {
            return JWT.create()
                    .withIssuer("Clinica API")
                    .withSubject(userDTO.getId())
                    .withClaim("login", userDTO.getLogin())
                    .withClaim("role", userDTO.getRole())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(expirationInstant())
                    .sign(secretAlgorithm());
        } catch (JWTCreationException exception) {
            throw new JWTException("Generating JWT Token error");
        }
    }

    public String getSubject(String token) {
        try {
            return JWT.require(secretAlgorithm())
                    .withIssuer("Clinica API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (RuntimeException exception) {
            throw new JWTException("JWT is invalid or is expirated!");
        }
    }

    private Instant expirationInstant() {
        return LocalDateTime.now().plusHours(Long.parseLong(this.expiration))
                .toInstant(ZoneOffset.of("-03:00"));

    }

    private Algorithm secretAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

}
