package com.project.clinicaapi.config.swaggerConfig.endPoint;

import com.project.clinicaapi.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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


    ResponseEntity<Page<UserResponseDTO>> findPage(Pageable pageable);

//    @Operation(summary = "Encontra um usuário por id.", security = {@SecurityRequirement(name = "bearer-key")})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))}),
//            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
//    })
    ResponseEntity<UserResponseDTO> findById(String userId);

}
