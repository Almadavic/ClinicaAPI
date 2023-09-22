package com.almada.clinicaapi.service.serviceAction.enableAccount;

import com.almada.clinicaapi.dto.request.EnableAccountDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.EnableAccountRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.serviceAction.EnableAccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class EnableAccountTest {

    @Autowired
    private EnableAccountService enableAccountService;

    @Autowired
    private UserFactory userFactory;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EnableAccountRepository enableAccountRepository;

    private User user;

    @BeforeEach
    void setUp() {
        User userFromFactory = userFactory.entity();
        userFromFactory.setRole(Role.SECRETARY);
        this.user = userRepository.save(userFromFactory);
    }

    @AfterEach
    void tearDown() {
        enableAccountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void generateCode() {

        Assertions.assertNull(user.getEnableAccount());

        enableAccountService.sendCodeToEmail(user);

        User userUpdate = returnUserDataBaseByLogin(user.getUsername());

        Assertions.assertNotNull(userUpdate.getEnableAccount());


    }

    @Test
    void enableAccountByCode() {

        enableAccountService.sendCodeToEmail(user);

        User userUpdate = returnUserDataBaseByLogin(user.getUsername());

        String password = "123456AB!";

        EnableAccountDTO enableAccountDTO = EnableAccountDTO.builder()
                .code(userUpdate.getEnableAccount().getCode())
                .password(password)
                .passwordConfirmation(password)
                .build();

        enableAccountService.enableAccount(enableAccountDTO);

        User userUpdateAgain = returnUserDataBaseByLogin(user.getUsername());

        Assertions.assertEquals(true, userUpdateAgain.isEnabled());
        Assertions.assertNotNull(userUpdateAgain.getPassword());

    }

    private User returnUserDataBaseByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }

}
