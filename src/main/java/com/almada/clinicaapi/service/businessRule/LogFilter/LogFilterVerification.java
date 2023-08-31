package com.almada.clinicaapi.service.businessRule.LogFilter;

import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.service.customException.DateOrderException;
import com.almada.clinicaapi.service.customException.InvalidDateFormatException;
import com.almada.clinicaapi.util.ConvertingType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
public abstract class LogFilterVerification {

    protected LogFilterVerification nextOne;

    public abstract Page<Log> verification(LogFilterArgs args);

    protected LocalDateTime converToLocalDateTime(String date, LocalTime localTime) {
        return LocalDateTime.of(convertToLocalDate(date), localTime);
    }

    private LocalDate convertToLocalDate(String date) {
        try {
            return ConvertingType.toLocalDateBrazilFormat(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    protected void dateOrder(LocalDateTime dateStart, LocalDateTime dateEnd, String dateStartString, String dateEndString) {

        if (dateStart.isAfter(dateEnd)) {
            throw new DateOrderException("The property 'datestart' value '" + dateStartString + "' " +
                    "cannot be after the 'dateend' value '" + dateEndString + "'");
        }

    }

}
