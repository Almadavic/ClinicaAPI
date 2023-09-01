package com.almada.clinicaapi.controller;


import com.almada.clinicaapi.config.swaggerConfig.endPoint.SecretarySwagger;
import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.serviceAction.SecretaryService;
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
@RequestMapping(value = "/secretaries")
@RequiredArgsConstructor
public class SecretaryController implements SecretarySwagger {

    private final SecretaryService secretaryService;

    @Override
    @PostMapping
    public ResponseEntity<SecretaryResponseDTO> save(@RequestBody @Valid SecretaryRegisterDTO secretaryDTO,
                                                     @AuthenticationPrincipal User userLogged,
                                                     UriComponentsBuilder uriBuilder) {

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.save(secretaryDTO, userLogged);

        URI uri = uriBuilder.path("/secretaries/{id}").buildAndExpand(secretaryResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(secretaryResponseDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<SecretaryResponseDTO>> findPage(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok().body(secretaryService.findPage(pageable));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SecretaryResponseDTO> findById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(secretaryService.findById(id));
    }

    @Override
    @GetMapping(value = "/registration/{registration}")
    public ResponseEntity<SecretaryResponseDTO> findByRegistration(@PathVariable(value = "registration") String registration) {
        return ResponseEntity.ok().body(secretaryService.findByRegistration(registration));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<SecretaryResponseDTO> update(@PathVariable(value = "id") String id,
                                                       @RequestBody @Valid SecretaryUpdateDTO secretaryDTO,
                                                       @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(secretaryService.update(id, secretaryDTO, userLogged));
    }

}
