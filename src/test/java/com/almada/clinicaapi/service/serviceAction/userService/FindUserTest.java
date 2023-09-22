package com.almada.clinicaapi.service.serviceAction.userService;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
class FindUserTest {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User admin;

    @BeforeEach
    void setUp() {
        this.admin = userFactory.entity();
    }

    @Test
    void userNotFoundByLogin() {

        String login = "doesntExist";

        when(userRepository.findByLogin(login)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.loadUserByUsername(login));

        Mockito.verify(userRepository).findByLogin(login);

    }

    @Test
    void userFoundByLogin() {


        when(userRepository.findByLogin(admin.getUsername())).thenReturn(Optional.of(admin));

        Assertions.assertDoesNotThrow(() -> userService.loadUserByUsername(admin.getUsername()));

        Mockito.verify(userRepository).findByLogin(admin.getUsername());

    }

    @Test
    void userNotFoundById() {


        admin.setRole(Role.ADMINISTRATOR);

        String invalidId = "9u9u9";

        when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.findById(invalidId));

        Mockito.verify(userRepository).findById(invalidId);

    }

//    @Test
//    void userFoundById() {
//
//        User admin = userFactory.entity();
//        admin.setRole(Role.ADMINISTRATOR);
//
//        when(userRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
//
//        Assertions.assertDoesNotThrow((() -> userService.disableAccount(admin.getId(), admin)));
//
//        Mockito.verify(userRepository).findById(admin.getId());
//
//    }

}
