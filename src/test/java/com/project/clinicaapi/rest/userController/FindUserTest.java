package com.project.clinicaapi.rest.userController;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class FindUserTest extends ClassTestParent {

    @Autowired
    private Factory factory;

    private final String path = "/users";

    @Test
    void findPageUsers() throws Exception {


        mockMvc.perform(get(path)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findUserByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        mockMvc.perform(get(path + "/{userid}", id)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The user id: " + id + " wasn't found on database", result.getResolvedException().getMessage()));

    }

    @Test
    void findUserByIdSuccess() throws Exception {

        mockMvc.perform(get(path + "/{userid}", factory.returnUserDataBaseByLogin("admin").getId())
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

}
