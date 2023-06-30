package com.project.clinicaapi.config.swaggerConfig.endPoint;

import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.project.clinicaapi.dto.response.AppointmentResponseDTO;
import com.project.clinicaapi.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Consultas", description = "Operações relacionadas á consultas")
public interface AppointmentSwagger {

    ResponseEntity<AppointmentResponseDTO> save(AppointmentRegisterDTO dentistDTO, User userLogged, UriComponentsBuilder uriBuilder);

    ResponseEntity<Page<AppointmentResponseDTO>> findPage(Pageable pageable);

    ResponseEntity<AppointmentResponseDTO> findById(String id);

    ResponseEntity<AppointmentResponseDTO> update(String id, AppointmentUpdateDTO appointmentDTO, User userLogged);

}
