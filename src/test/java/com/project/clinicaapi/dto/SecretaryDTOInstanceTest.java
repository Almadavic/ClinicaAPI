package com.project.clinicaapi.dto;

import com.project.clinicaapi.dto.response.PatientResponseDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.InvalidInstanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class SecretaryDTOInstanceTest implements UserDTOInterface {

    @Autowired
    private UserRepository userRepository;

    @Test
    void correctInstance() {

        Assertions.assertDoesNotThrow(() -> new SecretaryResponseDTO(returnUserDataBase("secretary", userRepository)));

    }

    @Test
    void wrongInstance() {

        Assertions.assertThrows(InvalidInstanceException.class
                ,() -> new SecretaryResponseDTO(returnUserDataBase("dentist1", userRepository)));

    }

}
