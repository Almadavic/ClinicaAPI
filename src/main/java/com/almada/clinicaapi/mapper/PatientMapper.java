package com.almada.clinicaapi.mapper;


import com.almada.clinicaapi.controller.PatientController;
import com.almada.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.enumerated.Gender;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class PatientMapper {

    public Patient toPatientEntity(PatientRegisterDTO patientDTO) {
        return Patient.patientBuilder()
                .login(patientDTO.getLogin())
                .email(patientDTO.getEmail())
                .name(patientDTO.getName())
                .cellphone(patientDTO.getCellphone())
                .gender(Gender.valueOf(patientDTO.getGender().toUpperCase()))
                .cpf(patientDTO.getCpf())
                .country(patientDTO.getAddress().getCountry())
                .state(patientDTO.getAddress().getState())
                .city(patientDTO.getAddress().getCity())
                .build();
    }

    public PatientResponseDTO toPatientDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO(patient);
        addHateoas(patientDTO);
        return patientDTO;
    }

    public Page<PatientResponseDTO> toPatientDTOPage(Page<Patient> patients) {
        Page<PatientResponseDTO> patientsDTO = patients.map(PatientResponseDTO::new);
        patientsDTO.forEach(this::addHateoas);
        return patientsDTO;
    }

    private void addHateoas(PatientResponseDTO patientDTO) {
        patientDTO.add(linkTo(methodOn(PatientController.class).findById(patientDTO.getId())).withSelfRel());
        patientDTO.add(linkTo(methodOn(PatientController.class).findPage(null)).withRel(IanaLinkRelations.COLLECTION));
        patientDTO.add(linkTo(methodOn(PatientController.class).update(patientDTO.getId(), null, null))
                .withRel("update"));
    }

}
