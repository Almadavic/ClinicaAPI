package com.almada.clinicaapi.config.swaggerConfig.endPoint;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.config.securityConfig.LoginData;
import com.almada.clinicaapi.config.securityConfig.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Autenticar no sistema")
public interface AuthenticationSwagger {

    @Operation(summary = "Logar no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Token.class))}),
            @ApiResponse(responseCode = "400", description = "E-mail e | ou senha está | estão errados | Conta inativa",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Token> authenticate(LoginData loginData) throws JsonProcessingException;

}