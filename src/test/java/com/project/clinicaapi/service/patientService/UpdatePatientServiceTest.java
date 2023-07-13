package com.project.clinicaapi.service.patientService;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.dto.response.PatientResponseDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.serviceAction.PatientService;
import com.project.clinicaapi.service.serviceAction.SecretaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdatePatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private Factory factory;

    @Test
    void fieldsValue() {

        String cpf = "131.541.237-02";

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder()
                .cpf(cpf)
                .build();

        PatientResponseDTO patientResponseDTO = patientService.update(factory.returnUserDataBaseByLogin("patient").getId(), patientDTO,
                factory.returnUserDataBaseByLogin("admin"));

        Assertions.assertEquals(cpf, patientResponseDTO.getCpf());

    }

}
