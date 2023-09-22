package com.almada.clinicaapi.service.serviceAction.secretaryService;

import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.SecretaryRepository;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.SecretaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class FindSecretaryTest {

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Autowired
    private SecretaryService secretaryService;

    @MockBean
    private SecretaryRepository secretaryRepository;

    private Secretary secretary;

    @BeforeEach
    void setUp() {
        this.secretary = secretaryFactory.entity();
    }

    @Test
    void secretaryNotFoundById() {

        String id = "idDoesntExist";

        when(secretaryRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> secretaryService.findById(id));

        Mockito.verify(secretaryRepository).findById(id);

    }

    @Test
    void secretaryFoundById() {

        when(secretaryRepository.findById(secretary.getId())).thenReturn(Optional.of(secretary));

        Assertions.assertDoesNotThrow(() -> secretaryService.findById(secretary.getId()));

        Mockito.verify(secretaryRepository).findById(secretary.getId());

    }

    @Test
    void secretaryNotFoundByRegistration() {

        String registration = "registrationDoesntExist";

        when(secretaryRepository.findByRegistration(registration)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> secretaryService.findByRegistration(registration));

        Mockito.verify(secretaryRepository).findByRegistration(registration);

    }

    @Test
    void secretaryFoundByRegistration() {

        when(secretaryRepository.findByRegistration(secretary.getRegistration())).thenReturn(Optional.of(secretary));

        Assertions.assertDoesNotThrow(() -> secretaryService.findByRegistration(secretary.getRegistration()));

        Mockito.verify(secretaryRepository).findByRegistration(secretary.getRegistration());

    }

}
