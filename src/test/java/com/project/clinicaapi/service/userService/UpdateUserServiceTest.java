package com.project.clinicaapi.service.userService;

import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.serviceAction.SecretaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateUserServiceTest {

    @Autowired
    private SecretaryService secretaryService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void fieldsValue() {

        String login = "login123";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .login(login)
                .build();

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.update(returnSecretaryId(), secretaryDTO, userLogged());

        Assertions.assertEquals(login, secretaryResponseDTO.getLogin());

    }

    private String returnSecretaryId() {
        return userRepository.findByLogin("secretary").get().getId();
    }

    private User userLogged() {
        return userRepository.findByLogin("admin").get();
    }

}
