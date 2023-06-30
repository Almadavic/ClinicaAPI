package com.project.clinicaapi.rest.appointmentController;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.repository.AppointmentRepository;
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
class FindAppointmentTest extends ClassTestParent {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private final String path = "/appointments";

    @Test
    void findPageAppointments() throws Exception {

        mockMvc.perform(get(path)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findAppointmentByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        mockMvc.perform(get(path + "/{appointmentid}", id)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The appointment id: " + id + " wasn't found on database", result.getResolvedException().getMessage()));

    }

    @Test
    void findDentistByIdSuccess() throws Exception {

        mockMvc.perform(get(path + "/{appointmentid}", returnAppointmentDataBase().getId())
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    private Appointment returnAppointmentDataBase() {
        return appointmentRepository.findAll().get(0);
    }


}
