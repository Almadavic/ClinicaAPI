package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.factory.AppointmentFactory;
import com.almada.clinicaapi.repository.AppointmentRepository;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.service.customException.AnotherMeetingRunningException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.Collections;

@ActiveProfiles(value = "test")
@SpringBootTest
class AvailablePersonTimeTest {

    @Autowired
    private AppointmentFactory appointmentFactory;

    @Test
    void timeStartWithinAnotherAppointment() {

        Assertions.assertThrows(AnotherMeetingRunningException.class,
                () -> AvailablePersonTime.verification(Collections.singletonList(appointmentFactory.entity(null, null)),
                        LocalTime.now().minusMinutes(5), LocalTime.now().plusMinutes(30), null));

    }

    @Test
    void timeEndWithinAnotherAppointment() {

        Assertions.assertThrows(AnotherMeetingRunningException.class,
                () -> AvailablePersonTime.verification(Collections.singletonList(appointmentFactory.entity(null, null)),
                        LocalTime.now().minusMinutes(35), LocalTime.now().plusMinutes(5), null));

    }

    @Test
    void timeStartDTOBeforeExistingAppointmentAndTimeEndDTOAfter() {

        Assertions.assertThrows(AnotherMeetingRunningException.class,
                () -> AvailablePersonTime.verification(Collections.singletonList(appointmentFactory.entity(null, null)),
                        LocalTime.now().minusMinutes(5), LocalTime.now().plusMinutes(55), null));

    }

    @Test
    void notAnyOtherAppointmentRunning() {

        Assertions.assertDoesNotThrow(() ->
                AvailablePersonTime.verification(Collections.singletonList(appointmentFactory.entity(null, null)),
                        LocalTime.now().plusHours(2), LocalTime.now().plusHours(4), null));

    }

}
