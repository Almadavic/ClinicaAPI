package com.almada.clinicaapi.mapper;

import com.almada.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.almada.clinicaapi.dto.response.AppointmentResponseDTO;
import com.almada.clinicaapi.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


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
        AppointmentResponseDTO appointmentDTO = new AppointmentResponseDTO(appointment);
        addHateoas(appointmentDTO);
        return appointmentDTO;
    }

    public Page<AppointmentResponseDTO> toAppointmentDTOPage(Page<Appointment> appointments) {
        Page<AppointmentResponseDTO> appointmentsDTO = appointments.map(AppointmentResponseDTO::new);
        appointmentsDTO.forEach(this::addHateoas);
        return appointmentsDTO;
    }

    private void addHateoas(AppointmentResponseDTO appointmentDTO) {

    }

}
