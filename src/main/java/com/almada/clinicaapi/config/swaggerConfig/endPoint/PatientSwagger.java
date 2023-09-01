package com.almada.clinicaapi.config.swaggerConfig.endPoint;

import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
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

@Tag(name = "Pacientes", description = "Operações relacionadas á pacientes")
public interface PatientSwagger {

    @Operation(summary = "Registra um paciente no sistema.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "O enum referente a gênero foi passado de forma incorreta. | " +
                    " Campos inválidos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "O e-mail, login, celular, cpf digitados já existem no sistema.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<PatientResponseDTO> save(PatientRegisterDTO patientDTO, User userLogged, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Encontra uma página de pacientes registrados", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
            @Parameter(in = ParameterIn.QUERY
                    , description = "Index da página que deseja acessar"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Quantidade de itens por página"
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "10"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Atributo de paciente pela qual a página será ordenada"
                    , name = "sort"
                    , content = @Content(schema = @Schema(type = "string", defaultValue = "name")))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacientes encontradas com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PatientResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<PatientResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Encontra um paciente por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<PatientResponseDTO> findById(String id);

    @Operation(summary = "Encontra um paciente por cpf.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<PatientResponseDTO> findByCpf(String cpf);

    @Operation(summary = "Atualiza um paciente por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "As senhas não são correspondentes. | " +
                    "O enum referente a gênero  foi passado de forma incorreta. | " +
                    "A senha foi passada sem a confirmação ou vice-versa. | Nenhum valor preenchido | Campos inválidos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "O e-mail, login, celular, cpf digitados já existem no sistema.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<PatientResponseDTO> update(String id, PatientUpdateDTO patientDTO, User userLogged);

}
