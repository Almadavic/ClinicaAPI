package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.DentistAvailableDayException;
import com.project.clinicaapi.util.ConvertingType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(value = 5)
@Component
public class DentistAvailableDay implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        WorkDayEnum workDay = ConvertingType.getWeekDayByLocalDate(args.appointmentDTO().getAppointmentDate());

        if(!dentistAvailable(args.dentist().getWorkDays(), workDay)) {
            throw new DentistAvailableDayException(workDay);
        }

    }

    private boolean dentistAvailable(List<WorkDay> dentistWorkDays, WorkDayEnum workDay) {
        return dentistWorkDays.stream().anyMatch(n -> n.getWorkDay().equals(workDay));
    }

}
