package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation.NoFieldFilledUpdateSecretary;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
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

    @Autowired
    private SecretaryFactory secretaryFactory;


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

        SecretaryUpdateDTO secretaryDTO = secretaryFactory.dtoUpdate();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null, null)));

    }

}
