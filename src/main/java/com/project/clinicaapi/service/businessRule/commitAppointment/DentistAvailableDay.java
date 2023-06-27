package com.project.clinicaapi.service.businessRule.commitAppointment;

import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.service.customException.DentistNotAvailableException;
import com.project.clinicaapi.util.ConvertingType;

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
