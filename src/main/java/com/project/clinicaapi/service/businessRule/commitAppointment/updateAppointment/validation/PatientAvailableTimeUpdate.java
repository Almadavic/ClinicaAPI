package com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.service.businessRule.commitAppointment.AvailablePersonTime;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Order(value = 10)
@Component
public class PatientAvailableTimeUpdate implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        LocalTime timeStart = args.appointmentDTO().getTimeStart();
        LocalTime timeEnd = args.appointment().getTimeEnd();

        if (timeStart != null) {
            List<Appointment> appointments = args.appointmentRepository().findByPatientAndByDate(args.patient().getId(), args.appointmentDTO().getAppointmentDate());
            AvailablePersonTime.verification(appointments, timeStart, timeEnd, "patient");
        }

    }

}
