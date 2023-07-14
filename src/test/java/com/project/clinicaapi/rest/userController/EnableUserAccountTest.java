package com.project.clinicaapi.rest.userController;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.EnableAccountDTO;
import com.project.clinicaapi.entity.EnableAccount;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.InvalidPasswordFormatException;
import com.project.clinicaapi.service.customException.PasswordDoesntMatchException;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class EnableUserAccountTest extends ClassTestParent {

    private final String path = "/users/enableaccount";

    @Autowired
    private Factory factory;

    @Test
    void passwordDontMatch() throws Exception {

        EnableAccountDTO enableAccountDTO = EnableAccountDTO.builder()
                .code("1971323571")
                .password("1234A!!")
                .passwordConfirmation("1234A!")
                .build();

        mockMvc.perform(patch(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(enableAccountDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasswordDoesntMatchException))
                .andExpect(result -> assertEquals("The value of the fields password and passwordconfirmation don't match",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void passwordWrongFormat() throws Exception {

        String password = "123467";

        EnableAccountDTO enableAccountDTO = EnableAccountDTO.builder()
                .code("1971334512")
                .password(password)
                .passwordConfirmation(password)
                .build();

        mockMvc.perform(patch(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(enableAccountDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidPasswordFormatException))
                .andExpect(result -> assertEquals("The password: " + password + " contains an invalid format",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void codeNotFound() throws Exception {

        String code = "aspjaioasj";

        EnableAccountDTO enableAccountDTO = EnableAccountDTO.builder()
                .code(code)
                .password("1234A!")
                .passwordConfirmation("1234A!")
                .build();

        mockMvc.perform(patch(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(enableAccountDTO)))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The code doesn't existis on dataBase",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void enableAccountSuccess() throws Exception {

        EnableAccountDTO enableAccountDTO = EnableAccountDTO.builder()
                .code("abcolpqstu")
                .password("1234A!F")
                .passwordConfirmation("1234A!F")
                .build();

        mockMvc.perform(patch(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(enableAccountDTO)))
                .andExpect(status().is(ok));

    }

}
