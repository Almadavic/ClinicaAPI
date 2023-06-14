package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.DentistSwagger;
import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.serviceAction.DentistService;
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
@RequestMapping(value = "/dentists")
@RequiredArgsConstructor
public class DentistController implements DentistSwagger {

    private final DentistService dentistService;

    @PostMapping
    public ResponseEntity<DentistResponseDTO> save(@RequestBody @Valid DentistRegisterDTO dentistDTO,
                                   @AuthenticationPrincipal User userLogged,
                                   UriComponentsBuilder uriBuilder) {

        DentistResponseDTO dentistResponseDTO = dentistService.save(dentistDTO, userLogged);

        URI uri = uriBuilder.path("/dentists/{id}").buildAndExpand(dentistResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(dentistResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DentistResponseDTO>> findPage(@PageableDefault(sort = "name") Pageable pageable,
                                                             @RequestParam(value = "name", required = false) String name,
                                                             @RequestParam(value = "specialty", required = false) String specialty) {

        return ResponseEntity.ok().body(dentistService.findPage(pageable, name, specialty));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DentistResponseDTO> findById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(dentistService.findById(id));
    }

    @GetMapping(value = "/cro/{cro}")
    public ResponseEntity<DentistResponseDTO> findByCro(@PathVariable(value = "cro") String cro) {
        return ResponseEntity.ok().body(dentistService.findByCro(cro));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<DentistResponseDTO> update(@PathVariable(value = "id") String id,
                                                     @RequestBody @Valid DentistUpdateDTO dentistDTO,
                                                     @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(dentistService.update(id, dentistDTO, userLogged));
    }

}
