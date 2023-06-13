package com.project.clinicaapi.rest.patientController;

import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class FindPatientTest extends ClassTestParent {

    private final String path = "/patients";

    @Autowired
    private UserRepository userRepository;

    @Test
    void findPagePatients() throws Exception {

        mockMvc.perform(get(path)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findSPatientByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        mockMvc.perform(get(path + "/{patientid}", id)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The patient id: " + id + " wasn't found on database", result.getResolvedException().getMessage()));

    }

    @Test
    void findPatientByIdSuccess() throws Exception {

        Patient patient = (Patient) userRepository.findByLogin("patient").get();

        mockMvc.perform(get(path + "/{patientid}", patient.getId())
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findPatientByCpfNotFound() throws Exception {

        String cpf = "120891481";

        mockMvc.perform(get(path + "/cpf/{cpf}", cpf)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The patient cpf: " + cpf + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void findPatientByCpfSuccess() throws Exception {

        mockMvc.perform(get(path + "/cpf/{cpf}", "115.613.986-02")
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

}
