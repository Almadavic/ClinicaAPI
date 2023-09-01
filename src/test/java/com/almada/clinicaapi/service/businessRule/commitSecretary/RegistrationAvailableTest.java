package com.almada.clinicaapi.service.businessRule.commitSecretary;

import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.repository.SecretaryRepository;
import com.almada.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;
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
class RegistrationAvailableTest {

    @Mock
    private SecretaryRepository secretaryRepository;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrationUnavailable() {

        when(secretaryRepository.findByRegistration("1156139862302")).thenReturn(Optional.of(secretaryFactory.entity()));

        Assertions.assertThrows(RegistrationAlreadyRegisteredException.class,
                () -> RegistrationAvailable.verification(secretaryRepository, "1156139862302"));
        Mockito.verify(secretaryRepository).findByRegistration("1156139862302");

    }

    @Test
    void registrationAvailable() {

        when(secretaryRepository.findByRegistration("11561398623")).thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> RegistrationAvailable.verification(secretaryRepository, "11561398623"));
        Mockito.verify(secretaryRepository).findByRegistration("11561398623");

    }

}
