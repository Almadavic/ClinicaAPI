package com.almada.clinicaapi.config.swaggerConfig.endPoint;

import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
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
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Secretárias", description = "Operações relacionadas á secretarias")
public interface SecretarySwagger {

    @Operation(summary = "Registra uma secretária no sistema.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Secretária cadastrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SecretaryResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "As senhas não são correspondentes. | " +
                    "O enum referente a gênero  foi passado de forma incorreta. | " +
                    "A senha foi passada sem a confirmação ou vice-versa. | Campos inválidos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "O e-mail, login, celular, número de registro digitados já existem no sistema.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<SecretaryResponseDTO> save(SecretaryRegisterDTO secretaryDTO, User userLogged, UriComponentsBuilder uriBuilder);


    @Operation(summary = "Encontra uma página de secretárias registrados", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
            @Parameter(in = ParameterIn.QUERY
                    , description = "Index da página que deseja acessar"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Quantidade de itens por página"
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "10"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Atributo de secretária pela qual a página será ordenada"
                    , name = "sort"
                    , content = @Content(schema = @Schema(type = "string", defaultValue = "name")))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretárias encontradas com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SecretaryResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<SecretaryResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Encontra uma secretária por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretária encontrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SecretaryResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Secretária não encontrada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<SecretaryResponseDTO> findById(String id);

    @Operation(summary = "Encontra uma secretária por número de registro.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretária encontrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SecretaryResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Secretária não encontrada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<SecretaryResponseDTO> findByRegistration(String registration);

    @Operation(summary = "Atualiza uma secretária por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Secretária atualizada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SecretaryResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "As senhas não são correspondentes. | " +
                    "O enum referente a gênero  foi passado de forma incorreta. | " +
                    "A senha foi passada sem a confirmação ou vice-versa. | Nenhum valor preenchido | Campos inválidos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Secretária  não encontrada no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "O e-mail, login, celular, número de registro digitados já existem no sistema.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<SecretaryResponseDTO> update(String id, SecretaryUpdateDTO secretaryDTO, User userLogged);

}
