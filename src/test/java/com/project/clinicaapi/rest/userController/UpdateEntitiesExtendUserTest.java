package com.project.clinicaapi.rest.userController;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class UpdateEntitiesExtendUserTest extends ClassTestParent {

    @Autowired
    private Factory factory;

    private final String path = "/secretaries";

    @Test
    void emailAlreadyExistsInTheSystem() throws Exception {

        String email = "admin@hotmail.com";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .email(email)
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary2").getId())
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

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
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

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
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


        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCellphoneNumberFormatException))
                .andExpect(result -> assertEquals("Cellphone number entered contains an invalid format: " + cellphone + ", Correct format: (XX)9XXXX-XXXX"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidNameFormat() throws Exception {

        String name = "123 Victor!";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .name(name)
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
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

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEmailFormatException))
                .andExpect(result -> assertEquals("The e-mail: " + email + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidPasswordFormat() throws Exception {

        String password = "123456a";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .password(password)
                .passwordConfirmation(password)
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidPasswordFormatException))
                .andExpect(result -> assertEquals("The password: " + password + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordNull() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .passwordConfirmation("123456")
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ParameterMissingException))
                .andExpect(result -> assertEquals("In order to register your account and set a password, you have to enter the fields 'password' and 'passwordconfirmation'."
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordConfirmationNull() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .password("123456")
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ParameterMissingException))
                .andExpect(result -> assertEquals("In order to register your account and set a password, you have to enter the fields 'password' and 'passwordconfirmation'."
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordsDontMatch() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .password("123456")
                .passwordConfirmation("1234567")
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasswordDoesntMatchException))
                .andExpect(result -> assertEquals("The value of the fields password and passwordconfirmation don't match"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void noAddressFieldFilledToUpdate() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .address(new AddressUpdateDTO())
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }


    @Test
    void addressFieldFilledToUpdate() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .address(AddressUpdateDTO.builder()
                        .city("São Paulo")
                        .build())
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(ok));

    }

    @Test
    void updateUserSuccess() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .cellphone("(31)99872-1540")
                .registration("1156139862392")
                .login("newLoginNovo")
                .password("1234567A")
                .passwordConfirmation("1234567A")
                .name("Novo nome Secretária")
                .address(new AddressUpdateDTO("country", "state", "city"))
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary3").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(ok));

    }

}
