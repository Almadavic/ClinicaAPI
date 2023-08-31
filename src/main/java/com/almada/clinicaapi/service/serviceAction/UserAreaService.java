package com.almada.clinicaapi.service.serviceAction;


import com.almada.clinicaapi.controller.UserAreaController;
import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import com.almada.clinicaapi.dto.response.*;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
@RequiredArgsConstructor
public class UserAreaService {

    private final UserService userService;

    private final SecretaryService secretaryService;

    private final PatientService patientService;

    private final DentistService dentistService;

    private final AppointmentService appointmentService;

    private final List<UpdateUserVerification> updateUserVerifications;

    public UserResponseDTO myProfile(User userLogged) {
        return verifyInstance(userLogged);
    }

    public Page<AppointmentResponseDTO> appointmentsByDentist(User userLogged, LocalDate appointmentDate, String patientName,
                                                              Pageable pageable) {

        return appointmentService.appointmentsByDentistId(userLogged.getId(), appointmentDate, patientName, pageable);
    }

    public Page<AppointmentResponseDTO> appointmentsByPatient(User userLogged, LocalDate apointmentDate, String dentistName,
                                                              Pageable pageable) {
        return appointmentService.appointmentsByPatientId(userLogged.getId(), apointmentDate, dentistName, pageable);
    }

    public UserResponseDTO changeProfileDataAsUserGeneric(UserUpdateDTO updateData, User userLogged) {
        updateUserVerifications.forEach(v -> v.verification(new UpdateUserArgs(updateData, userLogged)));
        return userService.update(updateData, userLogged);
    }

    public SecretaryResponseDTO changeProfileDataAsSecretary(SecretaryUpdateDTO updateData, User userLogged) {
        return secretaryService.update(userLogged.getId(), updateData, userLogged);
    }

    public DentistResponseDTO changeProfileDataAsDentist(DentistUpdateDTO updateData, User userLogged) {
        return dentistService.update(userLogged.getId(), updateData, userLogged);
    }

    public PatientResponseDTO changeProfileDataAsPatient(PatientUpdateDTO updateData, User userLogged) {

        return patientService.update(userLogged.getId(), updateData, userLogged);
    }

    private UserResponseDTO verifyInstance(User userLogged) {

        if (userLogged instanceof Secretary secretary) {
            SecretaryResponseDTO secretaryDTO = new SecretaryResponseDTO(secretary);
            secretaryDTO.add(linkTo(methodOn(UserAreaController.class).myProfile(null)).withSelfRel());
            secretaryDTO.add(linkTo(methodOn(UserAreaController.class).changeProfileDataAsSecretary(null, null)).withRel("UPDATE"));
            return secretaryDTO;
        } else if (userLogged instanceof Dentist dentist) {
            DentistResponseDTO dentistDTO = new DentistResponseDTO(dentist);
            dentistDTO.add(linkTo(methodOn(UserAreaController.class).myProfile(null)).withSelfRel());
            dentistDTO.add(linkTo(methodOn(UserAreaController.class).changeProfileDataAsDentist(null, null)).withRel("UPDATE"));
            dentistDTO.add(linkTo(methodOn(UserAreaController.class).appointmentsByDentist(null, null, null, null)).withRel("CONSULTAS"));
            return dentistDTO;
        } else if (userLogged instanceof Patient patient) {
            PatientResponseDTO patientDTO = new PatientResponseDTO(patient);
            patientDTO.add(linkTo(methodOn(UserAreaController.class).myProfile(null)).withSelfRel());
            patientDTO.add(linkTo(methodOn(UserAreaController.class).changeProfileDataAsPatient(null, null)).withRel("UPDATE"));
            patientDTO.add(linkTo(methodOn(UserAreaController.class).appointmentsByPatient(null, null, null, null)).withRel("CONSULTAS"));
            return patientDTO;
        } else {
            UserResponseDTO userDTO = new UserResponseDTO(userLogged);
            userDTO.add(linkTo(methodOn(UserAreaController.class).myProfile(null)).withSelfRel());
            userDTO.add(linkTo(methodOn(UserAreaController.class).changeProfileDataAsUserGeneric(null, null)).withRel("UPDATE"));
            return userDTO;
        }
    }

}
