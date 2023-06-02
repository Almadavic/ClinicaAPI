package com.project.clinicaapi.businessRule.logFilter;

import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.project.clinicaapi.service.businessRule.LogFilter.validation.DateEndNotNullWhileDateStartNull;
import com.project.clinicaapi.service.customException.ParameterMissingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class DateEndNotNullWhileDateStarNullTest {

    private DateEndNotNullWhileDateStartNull dateEndNotNullWhileDateStartNull;

    @BeforeEach
    void instantiate() {
        dateEndNotNullWhileDateStartNull = new DateEndNotNullWhileDateStartNull(null);
    }

    @Test
    void nullValue() {

        String dateEnd = "01/06/2023";

        Assertions.assertThrows(ParameterMissingException.class,
                () -> dateEndNotNullWhileDateStartNull.verification(new LogFilterArgs(null, null, null, dateEnd, null)));

    }

//    @Test
//    void filledValue() {
//
//        String dateStart = "29/05/2023";
//
//        String dateEnd = "01/06/2023";
//
//        Assertions.assertDoesNotThrow
//
//        Assertions.assertDoesNotThrow(ParameterMissingException.class,
//                () -> dateEndNotNullWhileDateStartNull.verification(new LogFilterArgs(null, null, dateStart, dateEnd, null)));
//
//    }


}
