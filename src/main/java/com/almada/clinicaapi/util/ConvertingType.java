package com.almada.clinicaapi.util;

import com.almada.clinicaapi.enumerated.WorkDayEnum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertingType {

    private ConvertingType() {

    }

    public static LocalDate toLocalDateBrazilFormat(String date) {

        if (date != null) {
            return LocalDate.parse(date, brazilFormat());
        }

        return null;
    }

    public static String toBrazilFormat(LocalDate date) {
            return date.format(brazilFormat());

    }

    public static WorkDayEnum getWeekDayByLocalDate(LocalDate appointmentDate) {
        return WorkDayEnum.valueOf(appointmentDate.getDayOfWeek().toString());
    }

    private static DateTimeFormatter brazilFormat() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

}
