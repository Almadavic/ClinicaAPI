package com.almada.clinicaapi.service.serviceAction.userService.disableUserAccountServiceTest;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.disableAccount.DisableAccountVerification;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class UserNotFoundTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private List<DisableAccountVerification> disableAccountVerifications;

    @Autowired
    private UserFactory userFactory;

    @Test
    void userNotFoundById() {

        User admin = userFactory.entity();
        admin.setRole(Role.ADMINISTRATOR);

        String invalidId = "9u9u9";

        when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.disableAccount(invalidId, admin));

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
