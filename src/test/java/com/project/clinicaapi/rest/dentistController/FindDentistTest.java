package com.project.clinicaapi.rest.dentistController;

import com.project.clinicaapi.entity.Dentist;
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
class FindDentistTest extends ClassTestParent {

    @Autowired
    private UserRepository userRepository;

    private final String path = "/dentists";

    @Test
    void findPageDentists() throws Exception {

        mockMvc.perform(get(path)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findDentistByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        mockMvc.perform(get(path + "/{dentistid}", id)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The dentist id: " + id + " wasn't found on database", result.getResolvedException().getMessage()));

    }

    @Test
    void findDentistByIdSuccess() throws Exception {

        Dentist dentist = (Dentist) userRepository.findByLogin("dentist1").get();

        mockMvc.perform(get(path + "/{dentistid}", dentist.getId())
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findDentistByCroNotFound() throws Exception {

        String cro = "12847";

        mockMvc.perform(get(path + "/cro/{cro}", cro)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The dentist cro: " + cro + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void findDentistByCroSuccess() throws Exception {

        mockMvc.perform(get(path + "/cro/{cro}", "137185")
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

}
