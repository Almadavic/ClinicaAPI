package com.almada.clinicaapi.controller;

import com.almada.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.dto.response.AppointmentResponseDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.serviceAction.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> save(@RequestBody @Valid AppointmentRegisterDTO dentistDTO,
                                                   @AuthenticationPrincipal User userLogged,
                                                   UriComponentsBuilder uriBuilder) {

        AppointmentResponseDTO appointmentResponseDTO = appointmentService.save(dentistDTO, userLogged);

        URI uri = uriBuilder.path("/appointments/{id}").buildAndExpand(appointmentResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(appointmentResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> findPage(@PageableDefault(sort = "appointmentDate") Pageable pageable) {
        return ResponseEntity.ok().body(appointmentService.findPage(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(appointmentService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(@PathVariable(value = "id") String id,
                                                     @RequestBody @Valid AppointmentUpdateDTO appointmentDTO,
                                                     @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(appointmentService.update(id, appointmentDTO, userLogged));
    }

}
