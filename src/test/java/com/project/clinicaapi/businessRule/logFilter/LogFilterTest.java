package com.project.clinicaapi.businessRule.logFilter;

import com.project.clinicaapi.repository.LogRepository;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import com.project.clinicaapi.service.businessRule.LogFilter.validation.*;
import com.project.clinicaapi.service.customException.DateOrderException;
import com.project.clinicaapi.service.customException.InvalidDateFormatException;
import com.project.clinicaapi.service.customException.ParameterMissingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class LogFilterTest {

    private LogFilterVerification verification;

    @Autowired
    private LogRepository logRepository;

    @BeforeEach
    void instantiate() {
        this.verification = new DateEndNotNullWhileDateStartNull(
                new FilterByDateStartAndDateEndAndUser(
                        new FilterByDateStartAndDateEnd(
                                new FilterByDateStartAndUser(
                                        new FilterByDateStart(
                                                new FilterByUser(
                                                        new NoFilters()))))));
    }

    @Test
    void invalidDateFormat() {

        String dateStart = "15/05/2023a";

        Assertions.assertThrows(InvalidDateFormatException.class,
                () -> verification.verification(new LogFilterArgs(null, null, dateStart, null, logRepository)));

    }

    @Test
    void validDateFormat() {

        String dateStart = "15/05/2023";

        Assertions.assertDoesNotThrow(() -> verification.verification(new LogFilterArgs(null, null, dateStart, null, logRepository)));

    }

    @Test
    void invalidOrder() {

        String dateStart = "10/05/2022";

        String dateEnd = "08/05/2022";

        Assertions.assertThrows(DateOrderException.class,
                () -> verification.verification(new LogFilterArgs(null, null, dateStart, dateEnd, logRepository)));

    }

    @Test
    void validOrder() {

        String dateStart = "08/05/2022";

        String dateEnd = "10/05/2022";

        Assertions.assertDoesNotThrow(() -> verification.verification(new LogFilterArgs(null, null, dateStart, dateEnd, logRepository)));

    }

    @Test
    void dateStartNull() {

        String dateEnd = "01/06/2023";

        Assertions.assertThrows(ParameterMissingException.class,
                () -> verification.verification(new LogFilterArgs(null, null, null, dateEnd, logRepository)));

    }


}
