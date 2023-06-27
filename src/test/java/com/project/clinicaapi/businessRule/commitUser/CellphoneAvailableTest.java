package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.CellphoneAvailable;
import com.project.clinicaapi.service.customException.CellphoneAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class CellphoneAvailableTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void cellphoneUnavailable() {

        Assertions.assertThrows(CellphoneAlreadyRegisteredException.class,
                () -> CellphoneAvailable.verification(userRepository, "(31)98858-8362"));

    }

    @Test
    void cellphoneAvailable() {

        Assertions.assertDoesNotThrow(() -> CellphoneAvailable.verification(userRepository, "(31)98858-8364"));

    }

}
