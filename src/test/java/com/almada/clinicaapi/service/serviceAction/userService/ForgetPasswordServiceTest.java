package com.almada.clinicaapi.service.serviceAction.userService;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.mapper.UserMapper;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.forgetPassword.ForgetPasswordVerification;
import com.almada.clinicaapi.service.customException.AccountNotEnabledException;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.AttributesListToUpdateService;
import com.almada.clinicaapi.service.serviceAction.ChangePasswordService;
import com.almada.clinicaapi.service.serviceAction.UserService;
import com.almada.clinicaapi.util.LogRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class ForgetPasswordServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ChangePasswordService changePasswordService;

    @Autowired
    private UserFactory userFactory;

    @Test
    void emailNotFound() {

        String email = "almadavic@live.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.forgetPassword(email));

        Mockito.verify(userRepository).findByEmail(email);

    }

    @Test
    void userNotEnabled() {

        String email = "almadavic@live.com";

        User user = userFactory.entity();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Assertions.assertThrows(AccountNotEnabledException.class,
                () -> userService.forgetPassword(email));

        Mockito.verify(userRepository).findByEmail(email);

    }

    @Test
    void success() {

        String email = "almadavic@live.com";

        User user = userFactory.entity();
        user.setEnabled(true);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Assertions.assertDoesNotThrow(() -> userService.forgetPassword(email));

        Mockito.verify(changePasswordService).sendCodeToEmail(user);
        Mockito.verify(userRepository).findByEmail(email);

    }

}
