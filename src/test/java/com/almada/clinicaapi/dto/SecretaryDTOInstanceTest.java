package com.almada.clinicaapi.dto;

import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
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
class SecretaryDTOInstanceTest  {

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Autowired
    private DentistFactory dentistFactory;

    @Test
    void correctInstance() {

        Assertions.assertDoesNotThrow(() -> new SecretaryResponseDTO(secretaryFactory.entity()));

    }

    @Test
    void wrongInstance() {

        Assertions.assertThrows(InvalidInstanceException.class
                ,() -> new SecretaryResponseDTO(dentistFactory.entity()));

    }

}
