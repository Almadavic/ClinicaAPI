package com.project.clinicaapi.service.enableAccount;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.EnableAccountDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.serviceAction.EnableAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class EnableAccountTest {

    @Autowired
    private EnableAccountService enableAccountService;

    @Autowired
    private Factory factory;

    @Test
    void generateCode() {

        User user = factory.returnUserDataBaseByLogin("dentist5");

        Assertions.assertNull(user.getEnableAccount());

        enableAccountService.sendCodeToEmail(user);

        User userUpdate = factory.returnUserDataBaseByLogin("dentist5");

        Assertions.assertNotNull(userUpdate.getEnableAccount());


    }

    @Test
    void enableAccountByCode() {

        User user = factory.returnUserDataBaseByLogin("dentist6");


        Assertions.assertFalse(user.isEnabled());
        Assertions.assertNull(user.getPassword());

        enableAccountService.sendCodeToEmail(user);

        User userUpdate = factory.returnUserDataBaseByLogin("dentist6");

        EnableAccountDTO enableAccountDTO = EnableAccountDTO.builder()
                .code(userUpdate.getEnableAccount().getCode())
                .password("123456AB!")
                .passwordConfirmation("123456AB!")
                .build();

        enableAccountService.enableAccount(enableAccountDTO);

        User userUpdateAgain = factory.returnUserDataBaseByLogin("dentist6");

        Assertions.assertEquals(true, userUpdateAgain.isEnabled());
        Assertions.assertNotNull(userUpdateAgain.getPassword());

    }

}
