package com.almada.clinicaapi.service.serviceAction.userService.disableUserAccountServiceTest;

import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@SpringBootTest
class FieldsValueTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void fieldsValue() {

        User admin = userFactory.entity();
        admin.setRole(Role.ADMINISTRATOR);

        Secretary secretary = secretaryFactory.entity();

        Assertions.assertTrue(secretary.isEnabled());

        saveUser(secretary);

        userService.disableAccount(secretary.getId(), admin);

        Optional<User> userDataBaseUpdated = userRepository.findById(secretary.getId());

        Assertions.assertFalse(userDataBaseUpdated.get().isEnabled());

    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }

}
