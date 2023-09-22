package com.almada.clinicaapi.service.serviceAction.userService;

import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import com.almada.clinicaapi.dto.response.UserResponseDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.mapper.UserMapper;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.almada.clinicaapi.service.serviceAction.AttributesListToUpdateService;
import com.almada.clinicaapi.service.serviceAction.UserService;
import com.almada.clinicaapi.util.LogRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateUserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AttributesListToUpdateService attributesListToUpdateService;

    @MockBean
    private List<UpdateUserVerification> updateUserVerifications;

    @MockBean
    private LogRegistration logRegistration;

    @Autowired
    private UserFactory userFactory;


    @Test
    void fieldsValue() {

        UserUpdateDTO userDTO = userFactory.dtoUpdate();

        User admin = userFactory.entity();
        admin.setRole(Role.ADMINISTRATOR);


        UserUpdateDTO u1 = userFactory.dtoUpdate();


        when(userRepository.save(admin)).thenReturn(admin);

        UserResponseDTO userResponseDTO = userService.update(u1, admin);
        Mockito.verify(userRepository).save(admin);
        // colocar o verify da lista

        Assertions.assertEquals(userDTO.getLogin(), userResponseDTO.getLogin());

    }

}
