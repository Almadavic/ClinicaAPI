package com.project.clinicaapi.businessRule.disableAccount;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.disableAccount.DisableAccountArgs;
import com.project.clinicaapi.service.businessRule.disableAccount.validation.PermissionToDisable;
import com.project.clinicaapi.service.customException.NoPermissionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class PermissionToDisableTest {

    @Autowired
    private PermissionToDisable service;

    @Autowired
    private UserRepository userRepository;

    @Test
    void authorizedToDisable() {
        testingThrowsException(returnUserDataBase("secretary"), returnUserDataBase("admin"));
    }

    @Test
    void notAuthorizedToDisable() {
        testingDoesNotThrowException(returnUserDataBase("secretary"), returnUserDataBase("secretary2"));
        testingDoesNotThrowException(returnUserDataBase("admin"), returnUserDataBase("secretary"));
        testingDoesNotThrowException(returnUserDataBase("admin"), returnUserDataBase("joaozin"));
    }

    private void testingThrowsException(User userLogged, User toBeDisable) {
        Assertions.assertThrows(NoPermissionException.class,
                () -> service.verification(new DisableAccountArgs(userLogged, toBeDisable)));
    }

    private void testingDoesNotThrowException(User userLogged, User toBeDisable) {
        Assertions.assertDoesNotThrow(() -> service.verification(new DisableAccountArgs(userLogged, toBeDisable)));
    }

    private User returnUserDataBase(String login) {
        return userRepository.findByLogin(login).get();
    }

}
