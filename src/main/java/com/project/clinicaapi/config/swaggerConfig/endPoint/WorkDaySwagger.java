package com.project.clinicaapi.config.swaggerConfig.endPoint;

import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Dias de trabalho", description = "Operações relacionadas á dias de trabalho")
public interface WorkDaySwagger {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dias de trabalho encontrados com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WorkDayResponseDTO.class)))})
    })
    ResponseEntity<List<WorkDayResponseDTO>> findAll();

    @Operation(summary = "Encontra um dia de trabalho por id.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dia de trabalho encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WorkDayResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Dia de trabalho não encontrado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<WorkDayResponseDTO> findById(Long id);

}
