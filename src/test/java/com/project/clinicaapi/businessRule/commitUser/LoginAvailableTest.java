package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.LoginAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class LoginAvailableTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void loginUnavailable() {

        Assertions.assertThrows(LoginAlreadyRegisteredException.class,
                () -> CommitUserValidations.findUserByLoginValidation(userRepository, "admin"));

    }

    @Test
    void loginAvailable() {

        Assertions.assertDoesNotThrow(() -> CommitUserValidations.findUserByLoginValidation(userRepository, "novo"));

    }

}
