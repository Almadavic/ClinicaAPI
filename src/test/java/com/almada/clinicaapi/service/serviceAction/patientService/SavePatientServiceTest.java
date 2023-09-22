package com.almada.clinicaapi.service.serviceAction.patientService;

import com.almada.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.factory.PatientFactory;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.almada.clinicaapi.service.serviceAction.EnableAccountService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SavePatientServiceTest {

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private EnableAccountService enableAccountService;

    @Autowired
    private PatientFactory patientFactory;

    @MockBean
    private List<RegisterUserVerification> registerUserVerifications;

    @MockBean
    private List<RegisterPatientVerification> registerPatientVerifications;

    @Test
    void fieldsValue() {

        PatientRegisterDTO patientDTO  = patientFactory.dtoRegister();
        Patient patient  = patientFactory.entity();

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        when(patientRepository.findByCpf(patient.getCpf())).thenReturn(Optional.empty()); // quando a lista funcionar o mock, é para remover esse when

        PatientResponseDTO patientResponseDTO = patientService.save(patientDTO, patient);

        Assertions.assertEquals(patientDTO.getCpf(), patientResponseDTO.getCpf());

        Mockito.verify(patientRepository).findByCpf(patient.getCpf());
        Mockito.verify(patientRepository).save(any(Patient.class));
        // colocar o verify da lista

    }

}
