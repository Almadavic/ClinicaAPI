package com.project.clinicaapi.util;

import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertingType {

    private ConvertingType() {

    }

    public static LocalDate toLocalDateBrazilFormat(String date) {

        if (date != null) {
            return LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        return null;
    }

    public static WorkDayEnum getWeekDayByLocalDate(LocalDate appointmentDate) {
        return WorkDayEnum.valueOf(appointmentDate.getDayOfWeek().toString());
    }
}
