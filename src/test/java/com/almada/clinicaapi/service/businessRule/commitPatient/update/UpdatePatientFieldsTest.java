package com.almada.clinicaapi.service.businessRule.commitPatient.update;

import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.factory.PatientFactory;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.validation.NoFieldFilledUpdatePatient;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
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

    @Autowired
    private PatientFactory patientFactory;

    @Test
    void noFieldFilledToUpdate() {

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder().build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdatePatientArgs(patientDTO, null, null)));

    }

    @Test
    void fieldFilledToUpdate() {

        PatientUpdateDTO patientDTOFieldsFilled = patientFactory.dtoUpdate();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdatePatientArgs(patientDTOFieldsFilled, null, null)));

    }


}
