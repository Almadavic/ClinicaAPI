package com.project.clinicaapi.service.userService;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.serviceAction.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class DisableUserAccountServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Factory factory;

    @Test
    void fieldsValue() {

        User userDataBase = factory.returnUserDataBaseByLogin("secretary");

        Assertions.assertTrue(userDataBase.isEnabled());

        userService.disableAccount(userDataBase.getId(), factory.returnUserDataBaseByLogin("admin"));

        User userDataBaseUpdated = factory.returnUserDataBaseByLogin("secretary");

        Assertions.assertFalse(userDataBaseUpdated.isEnabled());

    }


}
