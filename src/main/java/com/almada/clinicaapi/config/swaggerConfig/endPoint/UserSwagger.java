package com.almada.clinicaapi.config.swaggerConfig.endPoint;

import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.dto.request.ChangePasswordDTO;
import com.almada.clinicaapi.dto.request.EnableAccountDTO;
import com.almada.clinicaapi.dto.response.UserMonitoringResponseDTO;
import com.almada.clinicaapi.entity.User;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

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
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserMonitoringResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<UserMonitoringResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Encontra um usuário por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserMonitoringResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<UserMonitoringResponseDTO> findById(String userId);

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

    @Operation(summary = "Habilita um usuário por código.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário habilitado com sucesso.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Senha com formato incorreto | Senhas não correspondem",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Código não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<String> enableAccount(EnableAccountDTO enableAccountDTO);

    @Operation(summary = "Deleta um usuário por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<String> delete(String id, User userLogged);

    @Operation(summary = "Solicita alteração de senha para o e-mail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "E-mail enviado.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = " E-mail com formato inválido",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "E-mail não encontrado no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = " Usuário não está ativo",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<String> forgetPassword(String email);

    @Operation(summary = "Troca senha de um usuário por código.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta recuperada com sucesso.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Senha com formato incorreto | Senhas não correspondem",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Código não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<String> changePasswordForgotten(ChangePasswordDTO changePasswordDTO);

}
