package com.almada.clinicaapi.service.businessRule.commitSecretary.update;

import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.factory.SecretaryFactory;
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
class UpdateSecretaryFieldsTest {

    @Autowired
    private NoFieldFilledUpdateSecretary service;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Test
    void noFieldFilledToUpdate() {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null, null)));

    }


    @Test
    void fieldFilledToUpdate() {

        SecretaryUpdateDTO secretaryDTOFieldsFilled = secretaryFactory.dtoUpdate();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateSecretaryArgs(secretaryDTOFieldsFilled, null, null)));

    }

}
