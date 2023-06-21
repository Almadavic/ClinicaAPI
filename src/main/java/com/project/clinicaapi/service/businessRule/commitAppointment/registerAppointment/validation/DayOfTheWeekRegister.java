package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;
import com.project.clinicaapi.util.ConvertingType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class DayOfTheWeekRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        try {
            ConvertingType.getWeekDayByLocalDate(args.appointmentDTO().getAppointmentDate());
        }catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Sunday is not a valid day, we work from monday to saturday");
        }

    }

}
