package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.CellphoneAlreadyRegisteredException;
import com.almada.clinicaapi.service.customException.EmailAlreadyRegisteredException;
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
class EmailAvailableTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void emailUnavailable() {

        when(userRepository.findByEmail("email@hotmail.com")).thenReturn(Optional.of(userFactory.entity()));

        Assertions.assertThrows(EmailAlreadyRegisteredException.class,
                () -> EmailAvailable.verification(userRepository, "email@hotmail.com"));

        Mockito.verify(userRepository).findByEmail("email@hotmail.com");

    }

    @Test
    void emailAvailable() {

        when(userRepository.findByEmail("novo@hotmail.com")).thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> EmailAvailable.verification(userRepository, "novo@hotmail.com"));

    }



}
