package com.project.clinicaapi.rest.patientController;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.CpfAlreadyRegisteredException;
import com.project.clinicaapi.service.customException.InvalidCpfFormatException;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
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
class UpdatePatientTest extends ClassTestParent {

    @Autowired
    private Factory factory;

    private final String path = "/patients";

    @Test
    void patientByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder().build();

        mockMvc.perform(patch(path + "/{dentistid}", id)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The patient id: " + id + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void cpfAlreadyExistsInTheSystem() throws Exception {

        String cpf = "115.613.982-01";

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder()
                .cpf(cpf)
                .build();


        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("secretary"))
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CpfAlreadyRegisteredException))
                .andExpect(result -> assertEquals("The cpf: " + cpf + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidCpfFormat() throws Exception {

        String cpf = "913.7135012-08";

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder()
                .cpf(cpf)
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("patient").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCpfFormatException))
                .andExpect(result -> assertEquals("The cpf: " + cpf + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void noFieldFilledToUpdate() throws Exception {

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder().build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("patient").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void updatePatientSuccess() throws Exception {

        PatientUpdateDTO patientDTO = PatientUpdateDTO.builder()
                .cpf("112.448.221-02")
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnUserDataBaseByLogin("patient").getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().is(ok));

    }

}
