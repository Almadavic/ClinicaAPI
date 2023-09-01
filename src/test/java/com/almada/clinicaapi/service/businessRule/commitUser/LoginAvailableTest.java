package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.LoginAlreadyRegisteredException;
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
class LoginAvailableTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginUnavailable() {

        when(userRepository.findByLogin("login")).thenReturn(Optional.of(userFactory.entity()));

        Assertions.assertThrows(LoginAlreadyRegisteredException.class,
                () -> LoginAvailable.verification(userRepository, "login"));

        Mockito.verify(userRepository).findByLogin("login");
    }

    @Test
    void loginAvailable() {

        when(userRepository.findByLogin("login2")).thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> LoginAvailable.verification(userRepository, "login2"));

        Mockito.verify(userRepository).findByLogin("login2");
    }

}
