package com.almada.clinicaapi.controller;

import com.almada.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.serviceAction.PatientService;
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
@RequestMapping(value = "/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> save(@RequestBody @Valid PatientRegisterDTO patientDTO,
                                                     @AuthenticationPrincipal User userLogged,
                                                     UriComponentsBuilder uriBuilder) {

        PatientResponseDTO patientResponseDTO = patientService.save(patientDTO, userLogged);

        URI uri = uriBuilder.path("/patients/{id}").buildAndExpand(patientResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(patientResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> findPage(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok().body(patientService.findPage(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(patientService.findById(id));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<PatientResponseDTO> findByCpf(@PathVariable(value = "cpf") String cpf) {
        return ResponseEntity.ok().body(patientService.findByCpf(cpf));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable(value = "id") String id,
                                                       @RequestBody @Valid PatientUpdateDTO patientDTO,
                                                       @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(patientService.update(id, patientDTO, userLogged));
    }

}
