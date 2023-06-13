package com.project.clinicaapi.businessRule.commitPatient;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.validation.NoFieldFilledUpdatePatient;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation.NoFieldFilledUpdateSecretary;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdatePatientFieldsTest {

    @Autowired
    private NoFieldFilledUpdatePatient service;

    @Test
    void noFieldFilledToUpdate() {

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder().build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdatePatientArgs(patientDTO, null)));

    }

    @Test
    void fieldFilledToUpdate() {

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder()
                .cpf("91371387113")
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdatePatientArgs(patientDTO, null)));

    }


}
