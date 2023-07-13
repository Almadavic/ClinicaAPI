package com.project.clinicaapi.rest.userController;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.config.securityConfig.LoginData;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.DatabaseException;
import com.project.clinicaapi.service.customException.DisableOwnAccountException;
import com.project.clinicaapi.service.customException.NoPermissionException;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class DisableUserAccountTest extends ClassTestParent {

    @Autowired
    private Factory factory;

    private final String path = "/users";

    @Test
    void disableUserByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        mockMvc.perform(patch(path + "/disable/{userid}", id)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json"))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The user id: " + id + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }


    @Test
    void disableUserNoPermission() throws Exception {

        mockMvc.perform(patch(path + "/disable/{userid}", factory.returnUserDataBaseByLogin("admin").getId())
                        .header("Authorization", token("secretary", "123456"))
                        .contentType("application/json"))
                .andExpect(status().is(forbidden))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoPermissionException))
                .andExpect(result -> assertEquals("You don't have permission to: disable an administrator",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void disableOwnUserLoggedAccount() throws Exception {

        mockMvc.perform(patch(path + "/disable/{userid}", factory.returnUserDataBaseByLogin("secretary").getId())
                        .header("Authorization", token("secretary", "123456"))
                        .contentType("application/json"))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DisableOwnAccountException))
                .andExpect(result -> assertEquals("You cannot disable your own account",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void disableUserSuccess() throws Exception {

        enterSystemSuccess("secretary2", "123456");

        mockMvc.perform(patch(path + "/disable/{userid}", factory.returnUserDataBaseByLogin("secretary2").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json"))
                .andExpect(status().is(noContent));

        enterSystemFail("secretary2", "123456");

    }


    private void enterSystemSuccess(String login, String password) throws Exception {

        LoginData loginData = new LoginData(login, password);

        mockMvc.perform(post("/auth")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(ok));

    }

    private void enterSystemFail(String login, String password) throws Exception {

        LoginData loginData = new LoginData(login, password);

        mockMvc.perform(post("/auth")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DatabaseException))
                .andExpect(result -> assertEquals("Login and / or password is / are wrong | or your account is disabled",
                        result.getResolvedException().getMessage()));

    }

}
