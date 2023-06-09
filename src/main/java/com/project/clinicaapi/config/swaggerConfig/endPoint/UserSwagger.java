package com.project.clinicaapi.config.swaggerConfig.endPoint;

import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.project.clinicaapi.dto.request.EnableAccountDTO;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Usuários", description = "Operações relacionadas á usuários")
public interface UserSwagger {


    @Operation(summary = "Encontra uma página de usuários registrados", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
            @Parameter(in = ParameterIn.QUERY
                    , description = "Index da página que deseja acessar"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Quantidade de itens por página"
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "10"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Atributo de usuário pela qual a página será ordenada"
                    , name = "sort"
                    , content = @Content(schema = @Schema(type = "string", defaultValue = "name")))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontradas com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<UserResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Encontra um usuário por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<UserResponseDTO> findById(String userId);

    @Operation(summary = "Disabilita um usuário por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário desabilitado com sucesso",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Você não tem permissão para deletar um administrador",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Void> disableAccount(String id, User userLogged);

    @Operation(summary = "Habilita um usuário por código.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário habilitado com sucesso.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Senha com formato incorreto | Senhas não correspondem",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Código não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<String> enableAccount(@RequestBody @Valid EnableAccountDTO enableAccountDTO);

    @Operation(summary = "Deleta um usuário por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<String> delete(String id, User userLogged);

}
