package com.almada.clinicaapi.config.swaggerConfig.endPoint;

import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.dto.response.AppointmentResponseDTO;
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

@Tag(name = "Consultas", description = "Operações relacionadas á consultas")
public interface AppointmentSwagger {

    @Operation(summary = "Registra uma consulta no sistema.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta cadastrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Tempo de consulta não permitido, horário da consulta não permitido," +
                    "clinica não abre final de semana, data não pode ser passada, o dentista não esta disponivel nesse dia, o dentista" +
                    "tem uma outra consulta no mesmo horário, o dentista não está ativado no sistema, o paciente tem uma outra consulta no mesmo " +
                    "horário, o tempo da consulta não pode ser antigo.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<AppointmentResponseDTO> save(AppointmentRegisterDTO dentistDTO, User userLogged, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Encontra uma página de consultas registradas", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
            @Parameter(in = ParameterIn.QUERY
                    , description = "Index da página que deseja acessar"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Quantidade de itens por página"
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "10"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Atributo de consulta pela qual a página será ordenada"
                    , name = "sort"
                    , content = @Content(schema = @Schema(type = "string", defaultValue = "appointmentDate")))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultas encontrados com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<AppointmentResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Encontra uma consulta por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta encontrada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<AppointmentResponseDTO> findById(String id);

    @Operation(summary = "Atualiza uma consulta por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta atualizada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "Tempo de consulta não permitido, horário da consulta não permitido," +
                    "clinica não abre final de semana, data não pode ser passada, o dentista não esta disponivel nesse dia, o dentista" +
                    "tem uma outra consulta no mesmo horário, o dentista não está ativado no sistema, o paciente tem uma outra consulta no mesmo " +
                    "horário, o tempo da consulta não pode ser antigo.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<AppointmentResponseDTO> update(String id, AppointmentUpdateDTO appointmentDTO, User userLogged);

}
