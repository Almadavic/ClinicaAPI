package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation.NoFieldFilledUpdateSecretary;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateUserFieldsTest {

    @Autowired
    private NoFieldFilledUpdateSecretary service;


    @Test
    void noAddressFieldFilledToUpdate() {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .address(new AddressUpdateDTO())
                .build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null, null)));

    }

    @Test
    void addressFieldFilledToUpdate() {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .address(AddressUpdateDTO.builder()
                        .city("São Paulo")
                        .build())
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null, null)));

    }

}
