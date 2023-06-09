package com.project.clinicaapi.service.secretaryService;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.serviceAction.SecretaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class SaveSecretaryServiceTest {

    @Autowired
    private SecretaryService secretaryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    void noPasswordEntered() {

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("login")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("email@hotmail.com")
                .name("name nome")
                .cellphone("(31)98589-7284")
                .registration("91237198-B")
                .gender("MALE")
                .build();

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.save(secretaryDTO, returnUser());

        Secretary secretary = findSecretaryByLogin(secretaryResponseDTO.getLogin());

        Assertions.assertTrue(encoder.matches("123456", secretary.getPassword()));
        Assertions.assertEquals(Gender.MALE, Gender.valueOf(secretaryResponseDTO.getGender()));

    }

    @Test
    void passwordEntered() {

        String password = "12345678";

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("newlogin")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemaill@hotmail.com")
                .name("name nome")
                .password(password)
                .passwordConfirmation(password)
                .cellphone("(61)98589-7289")
                .registration("91237198-Z")
                .gender("MALE")
                .build();

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.save(secretaryDTO, returnUser());

        Secretary secretary = findSecretaryByLogin(secretaryResponseDTO.getLogin());

        Assertions.assertTrue(encoder.matches(password, secretary.getPassword()));

    }

    private Secretary findSecretaryByLogin(String login) {
        return (Secretary) userRepository.findByLogin(login).get();
    }

    private User returnUser() {
        return userRepository.findByLogin("admin").get();
    }

}
