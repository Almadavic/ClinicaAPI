package com.project.clinicaapi.rest.appointmentController;

import com.project.clinicaapi.rest.ClassTestParent;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class UpdateAppointmentTest extends ClassTestParent {

    private final String path = "/appointments";

}
