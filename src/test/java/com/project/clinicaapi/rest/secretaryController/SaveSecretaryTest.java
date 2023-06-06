package com.project.clinicaapi.rest.secretaryController;

import com.project.clinicaapi.config.securityConfig.LoginData;
import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.EmailAlreadyRegisteredException;
import com.project.clinicaapi.service.customException.LoginAlreadyRegisteredException;
import com.project.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class SaveSecretaryTest extends ClassTestParent {

    private final String path = "/secretaries";

    @Test
    void registrationAlreadyExistsInTheSystem() throws Exception {

        String registration = "1156139862302";

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("newlogin")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7284")
                .registration(registration)
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin","123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RegistrationAlreadyRegisteredException))
                .andExpect(result -> assertEquals("The registration: " + registration + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void saveSecretarySuccess() throws Exception {

        String login = "newLogin";

        String password = "1234567";

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login(login)
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7284")
                .password("1234567")
                .passwordConfirmation(password)
                .registration(password)
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin","123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(created));

        enterSystemUserSaved(login, password);

    }

    private void enterSystemUserSaved(String login, String senha) throws Exception {

        LoginData loginData = new LoginData(login, senha);

        mockMvc.perform(post("/auth")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(ok));

    }

}
