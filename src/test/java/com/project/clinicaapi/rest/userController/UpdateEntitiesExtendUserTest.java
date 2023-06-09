package com.project.clinicaapi.rest.userController;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class UpdateEntitiesExtendUserTest extends ClassTestParent {

    @Autowired
    private UserRepository userRepository;

    private final String path = "/secretaries";

    @Test
    void emailAlreadyExistsInTheSystem() throws Exception {

        String email = "admin@hotmail.com";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .email(email)
                .build();

        mockMvc.perform(patch(path + "/" + returnUser().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EmailAlreadyRegisteredException))
                .andExpect(result -> assertEquals("The email: " + email + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void loginAlreadyExistsInTheSystem() throws Exception {

        String login = "admin";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .login(login)
                .build();

        mockMvc.perform(patch(path + "/" + returnUser().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LoginAlreadyRegisteredException))
                .andExpect(result -> assertEquals("The login: " + login + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidGenderValue() throws Exception {

        String gender = "maleee";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .gender(gender)
                .build();

        mockMvc.perform(patch(path + "/" + returnUser().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEnumValueException))
                .andExpect(result -> assertEquals("The value you sent: " + gender + " to the type Gender is not valid, valid values: "
                                + ListEnumValues.returnEnumValues(Arrays.asList(Gender.values()))
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidCellphoneFormat() throws Exception {

        String cellphone = "31924701495aaff";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .cellphone(cellphone)
                .build();


        mockMvc.perform(patch(path + "/" + returnUser().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCellphoneNumberException))
                .andExpect(result -> assertEquals("Cellphone number entered contains an invalid format: " + cellphone + ", Correct format: (XX)9XXXX-XXXX"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidNameFormat() throws Exception {

        String name = "123 Victor!";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .name(name)
                .build();

        mockMvc.perform(patch(path + "/" + returnUser().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidNameFormatException))
                .andExpect(result -> assertEquals("The name: " + name + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidEmailFormat() throws Exception {

        String email = "aosjoas@hotmail.com!!";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .email(email)
                .build();

        mockMvc.perform(patch(path + "/" + returnUser().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEmailFormatException))
                .andExpect(result -> assertEquals("The e-mail: " + email + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    private User returnUser() {
        return userRepository.findByLogin("secretary2").get();
    }

}
