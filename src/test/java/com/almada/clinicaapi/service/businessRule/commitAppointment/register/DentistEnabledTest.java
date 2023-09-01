package com.almada.clinicaapi.service.businessRule.commitAppointment.register;

import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation.DentistEnabled;
import com.almada.clinicaapi.service.customException.UserNotEnable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class DentistEnabledTest {

    @Autowired
    private DentistEnabled service;

    @Autowired
    private DentistFactory dentistFactory;

    @Test
    void dentistNotEnabled() {

        Dentist dentist = dentistFactory.entity();

        Assertions.assertThrows(UserNotEnable.class,
                () -> service.verification(new RegisterAppointmentArgs(null, dentist ,null,
                        null)));

    }

    @Test
    void dentistEnabled() {

        Dentist dentist = dentistFactory.entity();
        dentist.setEnabled(true);

        Assertions.assertDoesNotThrow(() -> service.verification(new RegisterAppointmentArgs(null, dentist,
                null, null)));

    }

}
