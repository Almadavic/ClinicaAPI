package com.project.clinicaapi.rest.authentication;

import com.project.clinicaapi.config.securityConfig.LoginData;
import com.project.clinicaapi.rest.ClassTestParent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.project.clinicaapi.service.customException.DatabaseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationTest extends ClassTestParent {

    private final String path = "/auth";

    @Test
    void loginFailPasswordWrong() throws Exception {

        LoginData loginData = new LoginData("admin", "1234567");

        loginFail(loginData);

    }

    @Test
    void loginFailLoginWrong() throws Exception {

        LoginData loginData = new LoginData("adminn", "123456");

        loginFail(loginData);

    }

    @Test
    void loginFailAccountDisabled() throws Exception {

        LoginData loginData = new LoginData("inativo", "123456");

        loginFail(loginData);

    }

    @Test
    void loginOk() throws Exception {

        LoginData loginData = new LoginData("admin", "123456");

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(ok));

    }

    private void loginFail(LoginData loginData) throws Exception {

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DatabaseException))
                .andExpect(result -> assertEquals("Login and / or password is / are wrong | or your account is disabled"
                        , result.getResolvedException().getMessage()));

    }

}
