package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.UserAreaSwagger;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.dto.request.update.UserUpdateDTO;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.serviceAction.UserAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/userarea")
@RequiredArgsConstructor
public class UserAreaController implements UserAreaSwagger {

    private final UserAreaService userAreaService;

    @GetMapping(value = "/myprofile")
    public ResponseEntity<UserResponseDTO> myProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userAreaService.myProfile(user));
    }

    @PatchMapping(value = "/admin")
    public ResponseEntity<UserResponseDTO> changeProfileDataAsUserGeneric(@RequestBody @Valid UserUpdateDTO updateData,
                                                             @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsUserGeneric(updateData, userLogged));
    }

    @PatchMapping(value = "/secretary")
    public ResponseEntity<UserResponseDTO> changeProfileDataAsSecretary(@RequestBody @Valid SecretaryUpdateDTO updateData,
                                                                   @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsSecretary(updateData, userLogged));
    }

    @PatchMapping(value = "dentist")
    public ResponseEntity<UserResponseDTO> changeProfileDataAsDentist(@RequestBody @Valid DentistUpdateDTO updateData,
                                                                      @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsDentist(updateData, userLogged));
    }

    @PatchMapping(value = "/patient")
    public ResponseEntity<UserResponseDTO> changeProfileDataAsPatient(@RequestBody @Valid PatientUpdateDTO updateData,
                                                                      @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsPatient(updateData, userLogged));
    }

}
