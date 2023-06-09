package com.project.clinicaapi.rest.secretaryController;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import com.project.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;
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
class UpdateSecretaryTest extends ClassTestParent {

    @Autowired
    private UserRepository userRepository;

    private final String path = "/secretaries";

    @Test
    void registrationAlreadyExistsInTheSystem() throws Exception {

        String registration = "1151387";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .registration(registration)
                .build();

        mockMvc.perform(patch(path + "/" + returnSecretaryId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RegistrationAlreadyRegisteredException))
                .andExpect(result -> assertEquals("The registration: " + registration + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void noFieldFilledToUpdate() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .build();

        mockMvc.perform(patch(path + "/" + returnSecretaryId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void noAddressFieldFilledToUpdate() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .address(new AddressUpdateDTO())
                .build();

        mockMvc.perform(patch(path + "/" + returnSecretaryId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void fieldFilledToUpdate() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .registration("13781791")
                .build();


        mockMvc.perform(patch(path + "/" + returnSecretaryId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(ok));
    }

    @Test
    void addressFieldFilledToUpdate() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .address(AddressUpdateDTO.builder()
                        .city("São Paulo")
                        .build())
                .build();

        mockMvc.perform(patch(path + "/" + returnSecretaryId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(ok));

    }

    @Test
    void updateSecretarySuccess() throws Exception {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .registration("1156139862392")
                .build();

        mockMvc.perform(patch(path + "/" + returnSecretaryId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(ok));

    }

    private String returnSecretaryId() {
        return userRepository.findByLogin("secretary").get().getId();
    }

}
