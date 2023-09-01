package com.almada.clinicaapi.service.businessRule.disableAccount;

import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.service.businessRule.disableAccount.validation.DisableOwnAccount;
import com.almada.clinicaapi.service.customException.DisableOwnAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class DisableOwnAccountTest {

    @Autowired
    private DisableOwnAccount service;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Autowired
    private DentistFactory dentistFactory;

    @Test
    void deleteOwnAccount() {

        Secretary secretaty = secretaryFactory.entity();

        Assertions.assertThrows(DisableOwnAccountException.class,
                () -> service.verification(new DisableAccountArgs(secretaty, secretaty)));

    }

    @Test
    void deleteOtherAccountAccount() {

        Secretary secretaty = secretaryFactory.entity();

        Dentist dentist = dentistFactory.entity();

        Assertions.assertDoesNotThrow(() -> service.verification(new DisableAccountArgs(secretaty, dentist)));

    }

}
