package com.almada.clinicaapi.controller;

import com.almada.clinicaapi.config.swaggerConfig.endPoint.UserAreaSwagger;
import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import com.almada.clinicaapi.dto.response.*;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.serviceAction.UserAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    @GetMapping(value = "/dentist/appointments")
    public ResponseEntity<Page<AppointmentResponseDTO>> appointmentsByDentist(@AuthenticationPrincipal User user,
                                                                              @RequestParam(value = "appointment_date", required = false) LocalDate appointmentDate,
                                                                              @RequestParam(value = "patient_name", required = false) String patientName,
                                                                              @PageableDefault(sort = "appointmentDate") Pageable pageable) {

        return ResponseEntity.ok().body(userAreaService.appointmentsByDentist(user, appointmentDate, patientName, pageable));
    }

    @Override
    @GetMapping(value = "/patient/appointments")
    public ResponseEntity<Page<AppointmentResponseDTO>> appointmentsByPatient(@AuthenticationPrincipal User user,
                                                                              @RequestParam(value = "appointment_date", required = false) LocalDate appointmentDate,
                                                                              @RequestParam(value = "dentist_name", required = false) String dentistName,
                                                                              @PageableDefault(sort = "appointmentDate") Pageable pageable) {

        return ResponseEntity.ok().body(userAreaService.appointmentsByPatient(user, appointmentDate, dentistName, pageable));
    }

    @Override
    @PutMapping(value = "/admin")
    public ResponseEntity<UserResponseDTO> changeProfileDataAsUserGeneric(@RequestBody @Valid UserUpdateDTO updateData,
                                                                          @AuthenticationPrincipal User userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsUserGeneric(updateData, userLogged));
    }

    @Override
    @PutMapping(value = "/secretary")
    public ResponseEntity<SecretaryResponseDTO> changeProfileDataAsSecretary(@RequestBody @Valid SecretaryUpdateDTO updateData,
                                                                             @AuthenticationPrincipal Secretary userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsSecretary(updateData, userLogged));
    }

    @Override
    @PutMapping(value = "dentist")
    public ResponseEntity<DentistResponseDTO> changeProfileDataAsDentist(@RequestBody @Valid DentistUpdateDTO updateData,
                                                                         @AuthenticationPrincipal Dentist userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsDentist(updateData, userLogged));
    }

    @Override
    @PutMapping(value = "/patient")
    public ResponseEntity<PatientResponseDTO> changeProfileDataAsPatient(@RequestBody @Valid PatientUpdateDTO updateData,
                                                                         @AuthenticationPrincipal Patient userLogged) {

        return ResponseEntity.ok().body(userAreaService.changeProfileDataAsPatient(updateData, userLogged));
    }

}
