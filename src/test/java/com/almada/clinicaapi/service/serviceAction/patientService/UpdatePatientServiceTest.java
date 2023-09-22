package com.almada.clinicaapi.service.serviceAction.patientService;

import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.factory.PatientFactory;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.service.serviceAction.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdatePatientServiceTest {

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientFactory patientFactory;

    @Test
    void fieldsValue() {

        PatientUpdateDTO patientDTO = patientFactory.dtoUpdate();

        Patient patient = patientFactory.entity();

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);

        PatientResponseDTO patientResponseDTO = patientService.update(patient.getId(), patientDTO,
                patient);

        Assertions.assertEquals(patientDTO.getCpf(), patientResponseDTO.getCpf());

        Mockito.verify(patientRepository).findById(patient.getId());
        Mockito.verify(patientRepository).save(patient);
        // colocar o verify da lista

    }

}
