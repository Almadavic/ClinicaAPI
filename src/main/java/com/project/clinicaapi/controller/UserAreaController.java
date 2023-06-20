package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.UserAreaSwagger;
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

    @Override
    @GetMapping(value = "/myprofile")
    public ResponseEntity<UserResponseDTO> myProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userAreaService.myProfile(user));
    }

    @Override
    @PatchMapping(value = "/admin")
    public ResponseEntity<UserResponseDTO> changeProfileDataAsUserGeneric(@RequestBody @Valid UserUpdateDTO updateData,
                                                                          @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsUserGeneric(updateData, userLogged));
    }

    @Override
    @PatchMapping(value = "/secretary")
    public ResponseEntity<SecretaryResponseDTO> changeProfileDataAsSecretary(@RequestBody @Valid SecretaryUpdateDTO updateData,
                                                                             @AuthenticationPrincipal Secretary userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsSecretary(updateData, userLogged));
    }

    @Override
    @PatchMapping(value = "dentist")
    public ResponseEntity<DentistResponseDTO> changeProfileDataAsDentist(@RequestBody @Valid DentistUpdateDTO updateData,
                                                                         @AuthenticationPrincipal Dentist userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsDentist(updateData, userLogged));
    }

    @Override
    @PatchMapping(value = "/patient")
    public ResponseEntity<PatientResponseDTO> changeProfileDataAsPatient(@RequestBody @Valid PatientUpdateDTO updateData,
                                                                         @AuthenticationPrincipal Patient userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsPatient(updateData, userLogged));
    }

}
