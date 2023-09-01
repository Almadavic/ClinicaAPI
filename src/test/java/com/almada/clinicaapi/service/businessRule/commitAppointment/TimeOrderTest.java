package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.DateOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@ActiveProfiles(value = "test")
@SpringBootTest
class TimeOrderTest {

    @Test
    void timeEndBeforeTimeStart() {

        Assertions.assertThrows(DateOrderException.class,
                () -> TimeOrder.verification(LocalDate.now(), LocalTime.of(5, 40), LocalTime.of(5, 30)));

    }

    @Test
    void timeStartBeforeNow() {


        Assertions.assertThrows(DateOrderException.class,
                () -> TimeOrder.verification(LocalDate.now(), LocalTime.now().minusHours(1), LocalTime.now()));

    }

    @Test
    void timeEndAfterTimeStart() {

        Assertions.assertDoesNotThrow(
                () -> TimeOrder.verification(LocalDate.now(), LocalTime.now().plusHours(1), LocalTime.now().plusMinutes(90)));

    }

}
