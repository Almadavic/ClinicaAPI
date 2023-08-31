package com.almada.clinicaapi.mapper;

import com.almada.clinicaapi.controller.AppointmentController;
import com.almada.clinicaapi.controller.DentistController;
import com.almada.clinicaapi.controller.PatientController;
import com.almada.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.almada.clinicaapi.dto.response.AppointmentResponseDTO;
import com.almada.clinicaapi.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
        appointmentDTO.add(linkTo(methodOn(AppointmentController.class).findById(appointmentDTO.getId())).withSelfRel());
        appointmentDTO.add(linkTo(methodOn(AppointmentController.class).findPage(null)).withRel(IanaLinkRelations.COLLECTION));
        appointmentDTO.add(linkTo(methodOn(AppointmentController.class).update(appointmentDTO.getId(), null, null))
                .withRel("update"));
        appointmentDTO.getPatient().add(linkTo(methodOn(PatientController.class).findById(appointmentDTO.getPatient().getId())).withSelfRel());
        appointmentDTO.getDentist().add(linkTo(methodOn(DentistController.class).findById(appointmentDTO.getDentist().getId())).withSelfRel());
    }

}
