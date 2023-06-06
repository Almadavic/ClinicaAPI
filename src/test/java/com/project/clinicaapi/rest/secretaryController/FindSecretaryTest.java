package com.project.clinicaapi.rest.secretaryController;

import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.SecretaryRepository;
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
class FindSecretaryTest extends ClassTestParent {

    private final String path = "/secretaries";

    @Autowired
    private UserRepository userRepository;

    @Test
    void findPageSecretaries() throws Exception {

        mockMvc.perform(get(path)
                .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findSecretaryByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        mockMvc.perform(get(path+"/{userid}", id)
                .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The secretary id: " + id + " wasn't found on database", result.getResolvedException().getMessage()));

    }

    @Test
    void findSecretaryByIdSuccess() throws Exception {

        Secretary secretary = (Secretary) userRepository.findByLogin("secretary").get();

        mockMvc.perform(get(path+"/{userid}", secretary.getId())
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findSecretaryByRegistrationNotFound() throws Exception {

        String registration = "1393178asias";

        mockMvc.perform(get(path+"/registration/{registration}", registration)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The secretary registration: " + registration + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void findSecretaryByRegistrationSuccess() throws Exception {

        mockMvc.perform(get(path+"/registration/{registration}", "1156139862302")
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

}
