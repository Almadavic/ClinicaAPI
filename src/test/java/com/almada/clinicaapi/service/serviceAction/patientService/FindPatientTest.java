package com.almada.clinicaapi.service.serviceAction.patientService;

import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.factory.PatientFactory;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.PatientService;
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
class FindPatientTest {

    @Autowired
    private PatientFactory patientFactory;

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    private Patient patient;

    @BeforeEach
    void setUp() {
        this.patient = patientFactory.entity();
    }

    @Test
    void patientNotFoundById() {

        String id = "idDoesntExist";

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> patientService.findById(id));

        Mockito.verify(patientRepository).findById(id);

    }

    @Test
    void patientFoundById() {

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        Assertions.assertDoesNotThrow(() -> patientService.findById(patient.getId()));

        Mockito.verify(patientRepository).findById(patient.getId());

    }

    @Test
    void patientNotFoundByCpf() {

        String cpf = "cpfDoesntExist";

        when(patientRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> patientService.findByCpf(cpf));

        Mockito.verify(patientRepository).findByCpf(cpf);

    }

    @Test
    void patientFoundByCpf() {

        when(patientRepository.findByCpf(patient.getCpf())).thenReturn(Optional.of(patient));

        Assertions.assertDoesNotThrow(() -> patientService.findByCpf(patient.getCpf()));

        Mockito.verify(patientRepository).findByCpf(patient.getCpf());

    }

}
