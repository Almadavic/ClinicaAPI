package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.service.customException.WorkDayNumberSizeException;
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

        testingThrowsException(new HashSet<>(Arrays.asList(0, 5, 1, -1)));
        testingThrowsException(new HashSet<>(Arrays.asList(7, 9, 1)));

    }

    @Test
    void invalidValues() {

        testingDoesNotThrowException(new HashSet<>(Arrays.asList(1, 2, 3)));
        testingDoesNotThrowException(new HashSet<>(Arrays.asList(4, 3, 2)));

    }

    private void testingThrowsException(Set<Integer> workDays) {
        Assertions.assertThrows(WorkDayNumberSizeException.class,
                () -> WorkDayList.verification(workDays));
    }

    private void testingDoesNotThrowException(Set<Integer> workDays) {
        Assertions.assertDoesNotThrow(() -> WorkDayList.verification(workDays));
    }

}
