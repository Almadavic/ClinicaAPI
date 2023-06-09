package com.project.clinicaapi.rest.authorization;

import com.project.clinicaapi.rest.ClassTestParent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class AccessResourcesTest extends ClassTestParent {

    //---------------------- ALLOWED RESOURCES ----------------\\

    @Test
    void accessSwagger() throws Exception {

        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().is(ok));

    }

    @Test
    void accessAuthentication() throws Exception {

        mockMvc.perform(post("/auth"))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(notFound, result.getResponse().getStatus()));

    }

    @Test
    void accessInvalidResourceLogged() throws Exception {

        mockMvc.perform(delete("/INVALID")
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound));

    }

    @Test
    void accessMethodNotAllowedResourceLogged() throws Exception {

        mockMvc.perform(patch("/users")
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(methodNotAllowed));

    }


    //---------------------- DENIED RESOURCES ----------------\\

    @Test
    void accessInvalidResourceNoLogged() throws Exception {

        mockMvc.perform(get("/INVALID"))
                .andExpect(status().is(unauthorized));

    }

    @Test
    void accessMethodNotAllowedResourceNoLogged() throws Exception {

        mockMvc.perform(patch("/users"))
                .andExpect(status().is(unauthorized));

    }

}
