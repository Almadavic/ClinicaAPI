package com.almada.clinicaapi.config.swaggerConfig.endPoint;

import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.dto.response.DentistResponseDTO;
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

@Tag(name = "Dentistas", description = "Operações relacionadas á dentistas")
public interface DentistSwagger {

    @Operation(summary = "Registra um dentista no sistema.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dentista cadastrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DentistResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "O enum referente a gênero / specialty foi passado de forma incorreta. | " +
                    " Campos inválidos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "O e-mail, login, celular, cro digitados já existem no sistema.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<DentistResponseDTO> save(DentistRegisterDTO dentistDTO, User userLogged, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Encontra uma página de dentistas registrados", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
            @Parameter(in = ParameterIn.QUERY
                    , description = "Index da página que deseja acessar"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Quantidade de itens por página"
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "10"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Atributo de dentista pela qual a página será ordenada"
                    , name = "sort"
                    , content = @Content(schema = @Schema(type = "string", defaultValue = "name")))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentistas encontrados com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DentistResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort | Valor incorreto para Specialty",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<DentistResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable, String name, Boolean enabled, String specialty);

    @Operation(summary = "Encontra um dentista por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentista encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DentistResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Dentista não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<DentistResponseDTO> findById(String id);

    @Operation(summary = "Encontra um dentista por cro.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentista encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DentistResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Dentista não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<DentistResponseDTO> findByCro(String cro);

    @Operation(summary = "Atualiza um dentista por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentista atualizado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DentistResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "As senhas não são correspondentes. | " +
                    "O enum referente a gênero / specialty  foi passado de forma incorreta. | " +
                    "A senha foi passada sem a confirmação ou vice-versa. | Nenhum valor preenchido | Campos inválidos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Dentista não encontrado no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "O e-mail, login, celular, cro digitados já existem no sistema.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<DentistResponseDTO> update(String id, DentistUpdateDTO dentistDTO, User userLogged);

}
