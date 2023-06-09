package com.project.clinicaapi.rest.userController;

import com.project.clinicaapi.entity.User;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class DeleteUserTest extends ClassTestParent {

    @Autowired
    private UserRepository userRepository;

    private final String path = "/users";

    @Test
    void deleteUserByIdSuccess() throws Exception {

        mockMvc.perform(delete(path + "/{id}", returnUser())
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void deleteUserByIdNotFound() throws Exception {

        Long id = 90L;

        mockMvc.perform(delete(path + "/{id}", id)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The user id: " + id + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    private String returnUser() {
        return userRepository.findByLogin("delete").get().getId();
    }

}
