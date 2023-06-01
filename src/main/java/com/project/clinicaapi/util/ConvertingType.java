package com.project.clinicaapi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertingType {

    private ConvertingType() {

    }

    public static LocalDate toLocalDate(String date) {

        if(date!=null) {
            return LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        return null;
    }

    public static LocalDate toLocalDateBrazilFormat(String date) {

        if(date!=null) {
            return LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        return null;
    }

}
