package com.project.clinicaapi.rest.userController;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;
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
class RegisterEntitiesExtendUserTest extends ClassTestParent {

    private final String path = "/secretaries";

    @Test
    void emailAlreadyExistsInTheSystem() throws Exception {

        String email = "admin@hotmail.com";

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("newlogin")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email(email)
                .name("name nome")
                .cellphone("(61)98589-7284")
                .registration("1238917287128")
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
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

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login(login)
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7284")
                .registration("1937819371891")
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
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

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("novologinn")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newema@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7282")
                .registration("193781937189A")
                .gender(gender)
                .build();

        mockMvc.perform(post(path)
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

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("login1234")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("emailnovosergio@hotmail.com")
                .name("name nome")
                .cellphone(cellphone)
                .registration("193781B4N")
                .gender("male")
                .build();


        mockMvc.perform(post(path)
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

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("Larissa Oliveira")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("larissaoliveira@hotmail.com")
                .name(name)
                .cellphone("(61)98589-7984")
                .registration("1937b0419")
                .gender("male")
                .build();


        mockMvc.perform(post(path)
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

        SecretaryRegisterDTO secretaryDTO = SecretaryRegisterDTO.builder()
                .login("novologin")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email(email)
                .name("Sergio Augusto")
                .cellphone("(61)98589-7284")
                .registration("1937819371891")
                .gender("male")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEmailFormatException))
                .andExpect(result -> assertEquals("The e-mail: " + email + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

}
