package com.project.clinicaapi.service.userService;

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
    private UserRepository userRepository;

    @Test
    void fieldsValue() {

        User userDataBase = returnUserDataBase("secretary");

        Assertions.assertTrue(userDataBase.isEnabled());

        userService.disableAccount(userDataBase.getId(), returnUserDataBase("admin"));

        User userDataBaseUpdated = returnUserDataBase("secretary");

        Assertions.assertFalse(userDataBaseUpdated.isEnabled());

    }

    private User returnUserDataBase(String userName) {
        return userRepository.findByLogin(userName).get();
    }

}
