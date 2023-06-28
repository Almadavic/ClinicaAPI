package com.project.clinicaapi.businessRule.commitAppointment;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitAppointment.AvailablePersonTime;
import com.project.clinicaapi.service.customException.AnotherMeetingRunningException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.List;

@ActiveProfiles(value = "test")
@SpringBootTest
class AvailablePersonTimeTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Test
    void anotherAppointmentRunning() {


        Assertions.assertThrows(AnotherMeetingRunningException.class,
                () -> AvailablePersonTime.verification(getAppointmentList(), LocalTime.now(), LocalTime.now().minusMinutes(30),
                        null));

    }

    @Test
    void notAnyOtherAppointmentRunning() {


        Assertions.assertDoesNotThrow(() ->
                AvailablePersonTime.verification(getAppointmentList(), LocalTime.now().plusHours(2), LocalTime.now().plusHours(4),
                        null));

    }



    private List<Appointment> getAppointmentList() {
        return appointmentRepository.findAll();
    }


}
