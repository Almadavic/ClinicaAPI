package com.almada.clinicaapi.service.serviceAction.sendCodeToEmail;

import com.almada.clinicaapi.dto.request.ChangePasswordDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.ChangePasswordRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.serviceAction.ChangePasswordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class ChangePasswordForgottenTest {

    @Autowired
    private ChangePasswordService changePasswordService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChangePasswordRepository changePasswordRepository;

    @Autowired
    private UserFactory userFactory;

    private User user;

    @BeforeEach
    void setUp() {
        User userFromFactory = userFactory.entity();
        userFromFactory.setRole(Role.SECRETARY);
        this.user = userRepository.save(userFromFactory);
    }

    @AfterEach
    void tearDown() {
        changePasswordRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void generateCode() {

        Assertions.assertNull(user.getEnableAccount());

        changePasswordService.sendCodeToEmail(user);

        User userUpdated = returnUserDataBaseByLogin(user.getUsername());

        Assertions.assertNotNull(userUpdated.getChangePassword());
    }

    @Test
    void enableAccountByCode() {

        changePasswordService.sendCodeToEmail(user);

        User userUpdate = returnUserDataBaseByLogin(user.getUsername());

        String newPassword = "123456AB!";

        ChangePasswordDTO changePassword = ChangePasswordDTO.builder()
                .code(userUpdate.getChangePassword().getCode())
                .password(newPassword)
                .passwordConfirmation(newPassword)
                .build();

       changePasswordService.changePassword(changePassword);

        User userUpdateAgain = returnUserDataBaseByLogin(user.getUsername());

        Assertions.assertTrue(encoder.matches(newPassword, userUpdateAgain.getPassword()));

    }

    private User returnUserDataBaseByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }

}
