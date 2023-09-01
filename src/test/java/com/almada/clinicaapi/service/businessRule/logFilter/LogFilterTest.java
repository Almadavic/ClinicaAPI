package com.almada.clinicaapi.service.businessRule.logFilter;

import com.almada.clinicaapi.repository.LogRepository;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import com.almada.clinicaapi.service.businessRule.LogFilter.validation.*;
import com.almada.clinicaapi.service.customException.DateOrderException;
import com.almada.clinicaapi.service.customException.InvalidDateFormatException;
import com.almada.clinicaapi.service.customException.ParameterMissingException;
import com.almada.clinicaapi.util.ConvertingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@SpringBootTest
class LogFilterTest {

    private LogFilterVerification verification;

    @Mock
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

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void invalidDateFormat() {

        String dateStart = "15/05/2023a";

        Assertions.assertThrows(InvalidDateFormatException.class,
                () -> verification.verification(new LogFilterArgs(null, null, dateStart, null, null)));

    }

    @Test
    void validDateFormat() {

        String dateStart = "15/05/2023";

        LocalDate startDay = ConvertingType.toLocalDateBrazilFormat(dateStart);

        Assertions.assertDoesNotThrow(() -> verification.verification(new LogFilterArgs(null, null, dateStart, null, logRepository)));

        Mockito.verify(logRepository).findPageByDate
                (LocalDateTime.of(startDay, LocalTime.MIN),
                        LocalDateTime.of(startDay, LocalTime.MAX),
                        null);

    }

    @Test
    void invalidOrder() {

        String dateStart = "10/05/2022";

        String dateEnd = "08/05/2022";

        Assertions.assertThrows(DateOrderException.class,
                () -> verification.verification(new LogFilterArgs(null, null, dateStart, dateEnd, null)));

    }

    @Test
    void validOrder() {

        String dateStart = "08/05/2022";

        String dateEnd = "10/05/2022";

        LocalDate startDay = ConvertingType.toLocalDateBrazilFormat(dateStart);

        LocalDate endDay = ConvertingType.toLocalDateBrazilFormat(dateEnd);

        Assertions.assertDoesNotThrow(() -> verification.verification(new LogFilterArgs(null, null, dateStart, dateEnd, logRepository)));

        Mockito.verify(logRepository).findPageBetweenInterval
                (LocalDateTime.of(startDay, LocalTime.MIN),
                        LocalDateTime.of(endDay, LocalTime.MAX),
                        null);

    }

    @Test
    void dateStartNull() {

        String dateEnd = "01/06/2023";

        Assertions.assertThrows(ParameterMissingException.class,
                () -> verification.verification(new LogFilterArgs(null, null, null, dateEnd, null)));

    }


}
