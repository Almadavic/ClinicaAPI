package com.almada.clinicaapi.config.swaggerConfig.endPoint;

import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import com.almada.clinicaapi.dto.response.*;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.entity.Secretary;
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

import java.time.LocalDate;

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

    @Operation(summary = "Encontra uma página de consultas relacionadas ao dentista logado", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
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
            @ApiResponse(responseCode = "200", description = "Consultas encontradas com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<AppointmentResponseDTO>> appointmentsByDentist(User user, LocalDate appointmentDate, String dentistName,
                                                                       @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Encontra uma página de consultas relacionadas ao paciente logado", security = {@SecurityRequirement(name = "bearer-key")}, parameters = {
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
            @ApiResponse(responseCode = "200", description = "Consultas encontradas com sucesso",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Valor incorreto para sort",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Page<AppointmentResponseDTO>> appointmentsByPatient(User user, LocalDate appointmentDate, String dentistName,
                                                                       @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "USUARIO genérico(ADM) logado atualiza as informações da sua conta.", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SecretaryResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "As senhas não são correspondentes. | " +
                    "O enum referente a gênero  foi passado de forma incorreta. | " +
                    "A senha foi passada sem a confirmação ou vice-versa. | Nenhum valor preenchido | Campos inválidos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrada no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
            @ApiResponse(responseCode = "500", description = "O e-mail, login, celular digitados já existem no sistema.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    })
    ResponseEntity<UserResponseDTO> changeProfileDataAsUserGeneric(UserUpdateDTO updateData, User userLogged);

    @Operation(summary = "SECRETARIA logada atualiza as informações da sua conta.", security = {@SecurityRequirement(name = "bearer-key")})
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
    ResponseEntity<SecretaryResponseDTO> changeProfileDataAsSecretary(SecretaryUpdateDTO updateData, Secretary userLogged);

    @Operation(summary = "DENTISTA logado atualiza as informações de sua conta.", security = {@SecurityRequirement(name = "bearer-key")})
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
    ResponseEntity<DentistResponseDTO> changeProfileDataAsDentist(DentistUpdateDTO updateData, Dentist userLogged);

    @Operation(summary = "PACIENTE logado atualiza informações de sua conta.", security = {@SecurityRequirement(name = "bearer-key")})
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
    ResponseEntity<PatientResponseDTO> changeProfileDataAsPatient(PatientUpdateDTO updateData, Patient userLogged);

}
