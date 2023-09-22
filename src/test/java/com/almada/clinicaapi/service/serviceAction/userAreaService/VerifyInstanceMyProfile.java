package com.almada.clinicaapi.service.serviceAction.userAreaService;

import com.almada.clinicaapi.dto.response.DentistResponseDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
import com.almada.clinicaapi.dto.response.UserResponseDTO;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.factory.PatientFactory;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.service.serviceAction.UserAreaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class VerifyInstanceMyProfile {

    @Autowired
    private UserAreaService userAreaService;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private DentistFactory dentistFactory;

    @Autowired
    private PatientFactory patientFactory;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Test
    void verifySecretaryInstance() {

        UserResponseDTO userResponseDTO = userAreaService.myProfile(secretaryFactory.entity());
        Assertions.assertDoesNotThrow(() -> (SecretaryResponseDTO) userResponseDTO);
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/myprofile>;rel=\"self\""));
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/secretary>;rel=\"UPDATE\""));

    }

    @Test
    void verifyDentistInstance() {

        UserResponseDTO userResponseDTO = userAreaService.myProfile(dentistFactory.entity());
        Assertions.assertDoesNotThrow(() -> (DentistResponseDTO) userResponseDTO);
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/myprofile>;rel=\"self\""));
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/dentist>;rel=\"UPDATE\""));

    }

    @Test
    void verifyPatientInstance() {

        UserResponseDTO userResponseDTO = userAreaService.myProfile(patientFactory.entity());
        Assertions.assertDoesNotThrow(() -> (PatientResponseDTO) userResponseDTO);
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/myprofile>;rel=\"self\""));
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/patient>;rel=\"UPDATE\""));

    }

    @Test
    void verifyGenericInstance() {

        UserResponseDTO userResponseDTO = userAreaService.myProfile(userFactory.entity());
        Assertions.assertFalse(userResponseDTO instanceof SecretaryResponseDTO);
        Assertions.assertFalse(userResponseDTO instanceof DentistResponseDTO);
        Assertions.assertFalse(userResponseDTO instanceof PatientResponseDTO);
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/myprofile>;rel=\"self\""));
        Assertions.assertTrue(userResponseDTO.toString().contains("<http://localhost/userarea/admin>;rel=\"UPDATE\""));

    }

}
