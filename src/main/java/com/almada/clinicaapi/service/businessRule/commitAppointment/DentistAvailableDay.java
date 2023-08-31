package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.WorkDay;
import com.almada.clinicaapi.enumerated.WorkDayEnum;
import com.almada.clinicaapi.service.customException.DentistNotAvailableException;
import com.almada.clinicaapi.util.ConvertingType;

import java.time.LocalDate;
import java.util.List;

public class DentistAvailableDay {

    private DentistAvailableDay() {

    }

    public static void verification(LocalDate appointmentDate, Dentist dentist) {

        WorkDayEnum workDay = ConvertingType.getWeekDayByLocalDate(appointmentDate);

        if (!dentistAvailable(dentist.getWorkDays(), workDay)) {
            throw new DentistNotAvailableException(workDay);
        }

    }

    private static boolean dentistAvailable(List<WorkDay> dentistWorkDays, WorkDayEnum workDay) {
        return dentistWorkDays.stream().anyMatch(n -> n.getWorkDay().equals(workDay));
    }

}
