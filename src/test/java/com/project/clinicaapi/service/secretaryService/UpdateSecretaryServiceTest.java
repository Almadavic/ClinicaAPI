package com.project.clinicaapi.service.secretaryService;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.serviceAction.SecretaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateSecretaryServiceTest {

    @Autowired
    private SecretaryService secretaryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Factory factory;

    @Test
    void fieldsValue() {

        String registration = "1315415123";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .registration(registration)
                .build();

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.update(factory.returnUserDataBaseByLogin("secretary").getId(), secretaryDTO,
                factory.returnUserDataBaseByLogin("admin"));

        Assertions.assertEquals(registration, secretaryResponseDTO.getRegistration());

    }

}
