package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.service.customException.CroAlreadyRegisteredException;
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
class CroAvailableTest {

    @Mock
    private DentistRepository dentistRepository;

    @Autowired
    private DentistFactory dentistFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void croUnavailable() {

        when(dentistRepository.findByCro("137180")).thenReturn(Optional.of(dentistFactory.entity()));

        Assertions.assertThrows(CroAlreadyRegisteredException.class,
                () -> CroAvailable.verification(dentistRepository, "137180"));
        Mockito.verify(dentistRepository).findByCro("137180");

    }

    @Test
    void croAvailable() {

        when(dentistRepository.findByCro("12345")).thenReturn(Optional.empty());
        Assertions.assertDoesNotThrow(() -> CroAvailable.verification(dentistRepository, "12345"));
        Mockito.verify(dentistRepository).findByCro("12345");

    }

}
