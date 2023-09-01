package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.dto.response.AppointmentResponseDTO;
import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class AppointmentFactory {

    public Appointment entity(Dentist dentist, Patient patient) {
        return Appointment.builder()
                .procedure("Limpeza de dente")
                .appointmentDate(LocalDate.now())
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentist(dentist)
                .patient(patient)
                .build();
    }

   public AppointmentResponseDTO dtoResponse(Dentist dentist, Patient patient) {
        return new AppointmentResponseDTO(entity(dentist, patient));
   }

   public AppointmentRegisterDTO dtoRegister() {
        return AppointmentRegisterDTO.builder()
                .procedure("Limpeza de dente")
                .appointmentDate(LocalDate.now())
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusHours(1))
                .dentistId("0945e-98kdop")
                .patientId("0189380-2j9da9")
                .build();
   }

   public AppointmentUpdateDTO dtoUpdate() {
       return AppointmentUpdateDTO.builder()
               .procedure("Limpeza de dente")
               .appointmentDate(LocalDate.now())
               .timeStart(LocalTime.now())
               .timeEnd(LocalTime.now().plusHours(1))
               .build();
   }

}
