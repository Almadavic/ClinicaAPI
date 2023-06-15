package com.project.clinicaapi.businessRule.commitDentist;

import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.validation.NoFieldFilledUpdateDentist;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateDentistFieldsTest {

    @Autowired
    private NoFieldFilledUpdateDentist service;

    @Test
    void noFieldFilledToUpdate() {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder().build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateDentistArgs(dentistDTO, null, null, null)));

    }

    @Test
    void fieldFilledToUpdate() {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .speciality("othodontics")
                .cro("12752")
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateDentistArgs(dentistDTO, null, null, null)));

    }

    @Test
    void noWorkDayFieldFilledToUpdate() {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .workDays(new HashSet<>())
                .build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateDentistArgs(dentistDTO, null, null, null)));

    }

    @Test
    void workDayFieldFilledToUpdate() {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .workDays(new HashSet<>(Arrays.asList(1L, 2L, 4L)))
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateDentistArgs(dentistDTO, null, null, null)));

    }

}
