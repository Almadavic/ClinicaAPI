package com.almada.clinicaapi.dto;

import com.almada.clinicaapi.dto.response.DentistResponseDTO;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.service.customException.InvalidInstanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class DentistDTOInstanceTest {

    @Autowired
    private DentistFactory dentistFactory;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Test
    void correctInstance() {

        Assertions.assertDoesNotThrow(() -> new DentistResponseDTO(dentistFactory.entity()));

    }

    @Test
    void wrongInstance() {

        Assertions.assertThrows(InvalidInstanceException.class
                ,() -> new DentistResponseDTO(secretaryFactory.entity()));

    }

}
