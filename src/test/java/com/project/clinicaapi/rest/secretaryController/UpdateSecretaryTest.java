package com.project.clinicaapi.rest.secretaryController;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import com.project.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;
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
class UpdateSecretaryTest extends ClassTestParent {

    @Autowired
    private Factory factory;

    private final String path = "/secretaries";

    @Test
    void secretaryByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder().build();

        mockMvc.perform(patch(path + "/{dentistid}", id)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The secretary id: " + id + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void registrationAlreadyExistsInTheSystem() throws Exception {

        String registration = "1156139862302";

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .registration(registration)
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary2").getId())
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

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary2").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(secretaryDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }

}
