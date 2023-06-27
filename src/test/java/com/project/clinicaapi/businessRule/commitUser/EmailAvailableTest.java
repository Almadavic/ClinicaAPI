package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.EmailAvailable;
import com.project.clinicaapi.service.customException.EmailAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class EmailAvailableTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void emailUnavailable() {

        Assertions.assertThrows(EmailAlreadyRegisteredException.class,
                () -> EmailAvailable.verification(userRepository, "admin@hotmail.com"));

    }

    @Test
    void emailAvailable() {

        Assertions.assertDoesNotThrow(() -> EmailAvailable.verification(userRepository, "novo@hotmail.com"));

    }

}
