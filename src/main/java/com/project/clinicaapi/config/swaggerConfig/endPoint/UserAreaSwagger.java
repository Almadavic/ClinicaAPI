package com.project.clinicaapi.config.swaggerConfig.endPoint;

import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.dto.request.update.UserUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.dto.response.PatientResponseDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Área do Usuário", description = "Área onde o usuário visualiza suas informações")
public interface UserAreaSwagger {

    @Operation(summary = "Acessa as informações do usuário logado", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o perfil do usuário.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Você tem que estar logado para acessar esse recurso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<UserResponseDTO> myProfile(User userLogged);

    ResponseEntity<UserResponseDTO> changeProfileDataAsUserGeneric(UserUpdateDTO updateData, User userLogged);

    ResponseEntity<SecretaryResponseDTO> changeProfileDataAsSecretary(SecretaryUpdateDTO updateData, Secretary userLogged);

    ResponseEntity<DentistResponseDTO> changeProfileDataAsDentist(DentistUpdateDTO updateData, Dentist userLogged);

    ResponseEntity<PatientResponseDTO> changeProfileDataAsPatient(PatientUpdateDTO updateData, Patient userLogged);

}
