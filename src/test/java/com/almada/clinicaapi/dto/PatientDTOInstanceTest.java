package com.almada.clinicaapi.dto;

import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.factory.PatientFactory;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.service.customException.InvalidInstanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class PatientDTOInstanceTest {

    @Autowired
    private PatientFactory patientFactory;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Test
    void correctInstance() {

        Assertions.assertDoesNotThrow(() -> new PatientResponseDTO(patientFactory.entity()));

    }

    @Test
    void wrongInstance() {

        Assertions.assertThrows(InvalidInstanceException.class
                ,() -> new PatientResponseDTO(secretaryFactory.entity()));

    }

}
