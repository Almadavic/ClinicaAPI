package com.almada.clinicaapi.config.swaggerConfig.endPoint;

import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.dto.response.LogResponseDTO;
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


@Tag(name = "Logs", description = "Operações relacionadas á logs")
public interface LogSwagger {

    @Operation(summary = "Encontra uma página de logs registrados", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
            @Parameter(in = ParameterIn.QUERY
                    , description = "Index da página que deseja acessar"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Quantidade de itens por página"
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "10"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Atributo de log pela qual a página será ordenada"
                    , name = "sort"
                    , content = @Content(schema = @Schema(type = "string", defaultValue = "idlog")))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs encontradas com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LogResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort || Formato de data inválido",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<LogResponseDTO>> findPage(@Parameter(hidden = true) Pageable pageable,
                                                  String usuario,
                                                  @Parameter(description = "formato -> 22/04/2023")
                                                  String dateStart,
                                                  @Parameter(description = "formato -> 29/04/2023")
                                                  String dateEnd);

    @Operation(summary = "Encontra um log por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Log encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Log não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<LogResponseDTO> findById(Long id);

}
