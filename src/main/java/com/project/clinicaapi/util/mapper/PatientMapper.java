package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.project.clinicaapi.dto.response.PatientResponseDTO;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.enumerated.Gender;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


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
        return new PatientResponseDTO(patient);
    }

    public Page<PatientResponseDTO> toPatientDTOPage(Page<Patient> patients) {
        return patients.map(PatientResponseDTO::new);
    }

}
