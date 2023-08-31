package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder(value = {"id", "procedure", "appointmentDate", "timeStart", "timeEnd", "weekDay", "dentist", "patient"})
@Getter
public class AppointmentResponseDTO extends RepresentationModel<AppointmentResponseDTO> {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "procedure")
    private final String procedure;

    @JsonProperty(value = "appointmentDate")
    private final LocalDate appointmentDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT-3")
    @JsonProperty(value = "timeStart")
    private final LocalTime timeStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT-3")
    @JsonProperty(value = "timeEnd")
    private final LocalTime timeEnd;

    @JsonProperty(value= "weekDay")
    private final String weekDay;

    @JsonProperty(value = "dentist")
    private final AppointmentDentistResponseDTO dentist;

    @JsonProperty(value = "patient")
    private final AppointmentPatientResponseDTO patient;

    public AppointmentResponseDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.procedure = appointment.getProcedure();
        this.appointmentDate = appointment.getAppointmentDate();
        this.timeStart = appointment.getTimeStart();
        this.timeEnd = appointment.getTimeEnd();
        this.weekDay = appointment.getWeekDay().toString();
        this.dentist = new AppointmentDentistResponseDTO(appointment.getDentist());
        this.patient = new AppointmentPatientResponseDTO(appointment.getPatient());
    }

}
