package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(value = 8)
@Component
@RequiredArgsConstructor
public class PatientAvailableTimeRegister implements RegisterAppointmentVerification {

    private final AppointmentRepository appointmentRepository;

    @Override
    public void verification(RegisterAppointmentArgs args) {

        List<Appointment> appointments = appointmentRepository.findByPatientAndByDate(args.patient().getId(), args.appointmentDTO().getAppointmentDate());

        CommitAppointmentValidations.availableAppointmentTimeValidation(appointments, args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd(),
                "patient");

    }

}
