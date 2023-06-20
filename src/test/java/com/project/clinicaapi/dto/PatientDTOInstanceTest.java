package com.project.clinicaapi.dto;

import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.dto.response.PatientResponseDTO;
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
class PatientDTOInstanceTest implements UserDTOInterface{

    @Autowired
    private UserRepository userRepository;

    @Test
    void correctInstance() {

        Assertions.assertDoesNotThrow(() -> new PatientResponseDTO(returnUserDataBase("patient", userRepository)));

    }

    @Test
    void wrongInstance() {

        Assertions.assertThrows(InvalidInstanceException.class
                ,() -> new PatientResponseDTO(returnUserDataBase("secretary", userRepository)));

    }

}
