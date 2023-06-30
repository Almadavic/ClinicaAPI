package com.project.clinicaapi.businessRule.commitAppointment.register;

import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation.DentistEnabled;
import com.project.clinicaapi.service.customException.UserNotEnable;
import org.junit.jupiter.api.Assertions;
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
    private DentistRepository dentistRepository;




    @Test
    void dentistNotEnabled() {

        Assertions.assertThrows(UserNotEnable.class,
                () -> service.verification(new RegisterAppointmentArgs(null, returnDentistDataBase("00000") ,null,
                        null)));

    }

    @Test
    void dentistEnabled() {

        Assertions.assertDoesNotThrow(() -> service.verification(new RegisterAppointmentArgs(null, returnDentistDataBase("137180") ,
                null, null)));

    }

    private Dentist returnDentistDataBase(String cro) {
        return dentistRepository.findByCro(cro).get();
    }

}
