package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.AppointmentSwagger;
import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.response.AppointmentResponseDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.serviceAction.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/appointments")
@RequiredArgsConstructor
public class AppointmentController implements AppointmentSwagger {

    private final AppointmentService appointmentService;

    @Override
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> save(@RequestBody @Valid AppointmentRegisterDTO dentistDTO,
                                                   @AuthenticationPrincipal User userLogged,
                                                   UriComponentsBuilder uriBuilder) {

        AppointmentResponseDTO appointmentResponseDTO = appointmentService.save(dentistDTO, userLogged);

        URI uri = uriBuilder.path("/appointments/{id}").buildAndExpand(appointmentResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(appointmentResponseDTO);
    }

}
