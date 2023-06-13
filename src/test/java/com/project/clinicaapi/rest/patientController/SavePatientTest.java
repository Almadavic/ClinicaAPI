package com.project.clinicaapi.rest.patientController;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.CpfAlreadyRegisteredException;
import com.project.clinicaapi.service.customException.InvalidCpfFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class SavePatientTest extends ClassTestParent {

    private final String path = "/patients";

    @Test
    void cpfAlreadyExistsInTheSystem() throws Exception {

        String cpf = "115.613.986-02";

        PatientRegisterDTO patientDTO = PatientRegisterDTO.builder()
                .login("newlogin")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7284")
                .cpf(cpf)
                .gender("MALE")
                .build();


        mockMvc.perform(post(path)
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

        PatientRegisterDTO patientDTO = PatientRegisterDTO.builder()
                .login("newlogin")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemail@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7284")
                .cpf(cpf)
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCpfFormatException))
                .andExpect(result -> assertEquals("The cpf: " + cpf + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void savePatientSuccess() throws Exception {

        String login = "newLogin";

        String password = "1234567";

        PatientRegisterDTO patientDTO = PatientRegisterDTO.builder()
                .login("patientnovo")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newemailpatient@hotmail.com")
                .name("name nome")
                .cellphone("(71)98587-7284")
                .cpf("128.589.382-64")
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().is(created));

    }

}
