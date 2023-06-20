package com.project.clinicaapi.dto;

import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.InvalidInstanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class DentistDTOInstanceTest implements UserDTOInterface{

    @Autowired
    private UserRepository userRepository;

    @Test
    void correctInstance() {

        Assertions.assertDoesNotThrow(() -> new DentistResponseDTO(returnUserDataBase("dentist1", userRepository)));

    }

    @Test
    void wrongInstance() {

        Assertions.assertThrows(InvalidInstanceException.class
                ,() -> new DentistResponseDTO(returnUserDataBase("secretary", userRepository)));

    }

}
