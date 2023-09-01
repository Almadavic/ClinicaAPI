package com.almada.clinicaapi.service.businessRule.disableAccount;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.disableAccount.validation.PermissionToDisable;
import com.almada.clinicaapi.service.customException.NoPermissionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class PermissionToDisableTest {

    @Autowired
    private PermissionToDisable service;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Autowired
    private UserFactory userFactory;


    private User admin;

    @BeforeEach
    void setUp() {
        this.admin = userFactory.entity();
        this.admin.setRole(Role.ADMINISTRATOR);
    }


    @Test
    void notAuthorizedToDisable() {
        testingThrowsException(secretaryFactory.entity(), admin);
    }

    @Test
    void authorizedToDisable() {
        testingDoesNotThrowException(secretaryFactory.entity(), secretaryFactory.entityTwo());
        testingDoesNotThrowException(admin, secretaryFactory.entity());
        testingDoesNotThrowException(admin, secretaryFactory.entityTwo());
    }

    private void testingThrowsException(User userLogged, User toBeDisable) {
        Assertions.assertThrows(NoPermissionException.class,
                () -> service.verification(new DisableAccountArgs(userLogged, toBeDisable)));
    }

    private void testingDoesNotThrowException(User userLogged, User toBeDisable) {
        Assertions.assertDoesNotThrow(() -> service.verification(new DisableAccountArgs(userLogged, toBeDisable)));
    }

}
