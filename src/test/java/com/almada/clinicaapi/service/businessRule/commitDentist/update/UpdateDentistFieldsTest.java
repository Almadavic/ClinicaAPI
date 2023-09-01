package com.almada.clinicaapi.service.businessRule.commitDentist.update;

import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.validation.NoFieldFilledUpdateDentist;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
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

    @Autowired
    private DentistFactory dentistFactory;

    @Test
    void noFieldFilledToUpdate() {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder().build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateDentistArgs(dentistDTO, null, null, null)));

    }

    @Test
    void fieldFilledToUpdate() {

        DentistUpdateDTO dentistDTOFieldsFilled = dentistFactory.dtoUpdate();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateDentistArgs(dentistDTOFieldsFilled, null, null, null)));

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
                .workDays(new HashSet<>(Arrays.asList(1, 2, 4)))
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateDentistArgs(dentistDTO, null, null, null)));

    }

}
