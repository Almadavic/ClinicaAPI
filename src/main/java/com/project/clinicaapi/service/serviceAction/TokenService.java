package com.project.clinicaapi.service.serviceAction;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.customException.JWTException;
import com.project.clinicaapi.util.mapper.UserMapper;
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

        UserResponseDTO userDTO = mapper.toUserDTO((User) authentication.getPrincipal());

        try {
            return JWT.create()
                    .withIssuer("BioData API")
                    .withSubject(userDTO.getId())
                    .withClaim("login", userDTO.getEmail())
                    .withClaim("role", userDTO.getRole())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(expirationInstant())
                    .sign(secretAlgorithm());
        } catch (JWTCreationException exception) {
            throw new JWTException("Erro na geração do token JWT");
        }
    }

    public String getSubject(String token) {
        try {
            return JWT.require(secretAlgorithm())
                    .withIssuer("BioData API")
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

    private Algorithm secretAlgorithm () {
        return  Algorithm.HMAC256(secret);
    }

}
