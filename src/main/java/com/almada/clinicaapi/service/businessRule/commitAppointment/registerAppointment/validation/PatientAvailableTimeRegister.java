package com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.service.businessRule.commitAppointment.AvailablePersonTime;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(value = 9)
@Component
public class PatientAvailableTimeRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        List<Appointment> appointments = args.appointmentRepository().findByPatientAndByDate(args.patient().getId(), args.appointmentDTO().getAppointmentDate());
        AvailablePersonTime.verification(appointments, args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd(), "patient");

    }

}
