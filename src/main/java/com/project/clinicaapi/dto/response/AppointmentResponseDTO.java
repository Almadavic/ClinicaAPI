package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Appointment;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder(value = {"id", "procedure", "appointmentDate", "timeStart", "timeEnd", "weekDay", "dentistId", "patientId"})
@Getter
public class AppointmentResponseDTO {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "procedure")
    private final String procedure;

    @JsonProperty(value = "appointmentDate")
    private final LocalDate appointmentDate;

    @JsonProperty(value = "timeStart")
    private final LocalTime timeStart;

    @JsonProperty(value = "timeEnd")
    private final LocalTime timeEnd;

    @JsonProperty(value= "weekDay")
    private final WorkDayResponseDTO weekDay;

    @JsonProperty(value = "dentistId")
    private final String dentistId;

    @JsonProperty(value = "patientId")
    private final String patientId;

    public AppointmentResponseDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.procedure = appointment.getProcedure();
        this.appointmentDate = appointment.getAppointmentDate();
        this.timeStart = appointment.getTimeStart();
        this.timeEnd = appointment.getTimeEnd();
        this.dentistId = appointment.getDentist().getId();
        this.patientId = appointment.getPatient().getId();
        this.weekDay = new WorkDayResponseDTO(appointment.getWeekDay());
    }

}
