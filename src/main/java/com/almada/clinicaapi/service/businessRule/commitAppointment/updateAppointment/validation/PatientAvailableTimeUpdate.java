package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.service.businessRule.commitAppointment.AvailablePersonTime;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Order(value = 10)
@Component
public class PatientAvailableTimeUpdate implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        LocalDate appointmentDate = args.appointmentDTO().getAppointmentDate();
        LocalTime timeStart = args.appointmentDTO().getTimeStart();
        LocalTime timeEnd = args.appointmentDTO().getTimeEnd();

        if (timeStart != null || appointmentDate!= null) {
            List<Appointment> appointments = args.appointmentRepository().findByPatientAndByDate(args.appointment().getPatient().getId(),
                    verifyAppointmentDateNull(args.appointment().getAppointmentDate(), appointmentDate));
            AvailablePersonTime.verification(appointments, timeStart, timeEnd, "patient");
        }

    }

    private LocalDate verifyAppointmentDateNull(LocalDate appointmentDate, LocalDate appointmentDateDTO) {
        if(appointmentDateDTO != null) {
            return appointmentDateDTO;
        }
        return appointmentDate;
    }

}
