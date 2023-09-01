package com.almada.clinicaapi.service.businessRule.commitPatient;

import com.almada.clinicaapi.factory.PatientFactory;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.service.customException.CpfAlreadyRegisteredException;
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

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
class CpfAvailableTest {

    @Mock
    private PatientRepository patientRepository;

    @Autowired
    private PatientFactory patientFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cpfUnavailable() {

        when(patientRepository.findByCpf("115.613.986-02")).thenReturn(Optional.of(patientFactory.entity()));

        Assertions.assertThrows(CpfAlreadyRegisteredException.class,
                () -> CpfAvailable.verification(patientRepository, "115.613.986-02"));
        Mockito.verify(patientRepository).findByCpf("115.613.986-02");

    }

    @Test
    void cpfAvailable() {

        when(patientRepository.findByCpf("115.613.986-01")).thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> CpfAvailable.verification(patientRepository, "115.613.986-01"));
        Mockito.verify(patientRepository).findByCpf("115.613.986-01");

    }

}
