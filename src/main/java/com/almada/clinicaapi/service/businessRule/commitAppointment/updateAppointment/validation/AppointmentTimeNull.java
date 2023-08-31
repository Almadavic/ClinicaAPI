package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import com.almada.clinicaapi.service.customException.ParameterMissingException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class AppointmentTimeNull implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        if ((args.appointmentDTO().getTimeStart() != null && args.appointmentDTO().getTimeEnd() == null) ||
                (args.appointmentDTO().getTimeStart() == null && args.appointmentDTO().getTimeEnd() != null)) {
            throw new ParameterMissingException("In order to update an appointment and set a new time, you have to enter the fields 'timeStart' and 'timeEnd'");
        }

    }

}
