package com.project.clinicaapi.businessRule.disableAccount;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.disableAccount.DisableAccountArgs;
import com.project.clinicaapi.service.businessRule.disableAccount.validation.DisableOwnAccount;
import com.project.clinicaapi.service.customException.DisableOwnAccountException;
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
    private UserRepository userRepository;

    @Test
    void deleteOwnAccount() {

        cannotDeleteOwnAccount("admin");
        cannotDeleteOwnAccount("secretary2");
        cannotDeleteOwnAccount("secretary");

    }

    private void cannotDeleteOwnAccount(String login) {
        Assertions.assertThrows(DisableOwnAccountException.class,
                () -> service.verification(new DisableAccountArgs(returnUserDataBase(login), returnUserDataBase(login))));
    }

    private User returnUserDataBase(String login) {
        return userRepository.findByLogin(login).get();
    }

}
