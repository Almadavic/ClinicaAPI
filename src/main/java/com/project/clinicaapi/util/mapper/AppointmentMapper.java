package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.response.AppointmentResponseDTO;
import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;
import com.project.clinicaapi.util.ConvertingType;
import com.project.clinicaapi.util.ListEnumValues;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class AppointmentMapper {

    public Appointment toAppointmentEntity(AppointmentRegisterDTO appointmentDTO) {
        return Appointment.builder()
                .procedure(appointmentDTO.getProcedure())
                .appointmentDate(appointmentDTO.getAppointmentDate())
                .timeStart(appointmentDTO.getTimeStart())
                .timeEnd(appointmentDTO.getTimeEnd())
                .build();
    }

    public AppointmentResponseDTO toAppointmentDTO(Appointment appointment) {
        return new AppointmentResponseDTO(appointment);
    }

    public Page<AppointmentResponseDTO> toAppointmentDTOPage(Page<Appointment> appointments) {
        return appointments.map(AppointmentResponseDTO::new);
    }

}
