package com.project.clinicaapi.businessRule.commitDentist;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.customException.WorkDayNumberSizeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ActiveProfiles(value = "test")
@SpringBootTest
class WorkDayNumberSizeTest {

    @Test
    void validValues() {

        testingFail(new HashSet<>(Arrays.asList(0L, 5L, 1L, -1L)));
        testingFail(new HashSet<>(Arrays.asList(7L, 9L, 1L)));

    }

    @Test
    void invalidValues() {

        testingSuccess(new HashSet<>(Arrays.asList(1L, 2L, 3L)));
        testingSuccess(new HashSet<>(Arrays.asList(4L, 3L, 2L)));

    }

    private void testingFail(Set<Long> workDays) {
        Assertions.assertThrows(WorkDayNumberSizeException.class,
                () -> CommitDentistValidations.workdayListValidation(workDays));
    }

    private void testingSuccess(Set<Long> workDays) {
        Assertions.assertDoesNotThrow(() -> CommitDentistValidations.workdayListValidation(workDays));
    }

}
