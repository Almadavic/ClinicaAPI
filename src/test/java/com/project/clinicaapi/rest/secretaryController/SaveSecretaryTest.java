package com.project.clinicaapi.rest.secretaryController;

import com.project.clinicaapi.config.securityConfig.LoginData;
import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.*;
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
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RegistrationAlreadyRegisteredException))
                .andExpect(result -> assertEquals("The registration: " + registration + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordNull() throws Exception {

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("newlogin1")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail1@hotmail.com")
                .name("name nome")
                .cellphone("(61)98582-7284")
                .registration("115613986230257")
                .gender("MALE")
                .passwordConfirmation("123456")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasswordNullException))
                .andExpect(result -> assertEquals("In order to register your account and set a password, you have to enter the fields 'password' and 'passwordconfirmation'."
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordConfirmationNull() throws Exception {

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("newlogin2")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail2@hotmail.com")
                .name("name nome")
                .cellphone("(61)98579-7284")
                .registration("11561398623025")
                .gender("MALE")
                .password("123456")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasswordNullException))
                .andExpect(result -> assertEquals("In order to register your account and set a password, you have to enter the fields 'password' and 'passwordconfirmation'."
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordsDontMatch() throws Exception {

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("newlogin3")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail3@hotmail.com")
                .name("name nome")
                .cellphone("(61)98989-7284")
                .registration("115613986230255785")
                .gender("MALE")
                .password("123456")
                .passwordConfirmation("1234567")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasswordDoesntMatchException))
                .andExpect(result -> assertEquals("The value of the fields password and passwordconfirmation don't match"
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
                        .header("Authorization", token("admin", "123456"))
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
