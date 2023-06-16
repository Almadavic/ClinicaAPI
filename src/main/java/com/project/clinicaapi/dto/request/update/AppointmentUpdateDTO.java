package com.project.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.dto.response.WorkDayResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder(value = {"procedure", "appointmentDate", "timeStart", "timeEnd", "dentistId", "patientId"})
public class AppointmentUpdateDTO {

    @JsonProperty(value = "procedure")
    private String procedure;

    @JsonProperty(value = "appointmentDate")
    private LocalDate appointmentDate;

    @JsonProperty(value = "timeStart")
    private LocalTime timeStart;

    @JsonProperty(value = "timeEnd")
    private LocalTime timeEnd;

    @JsonProperty(value = "dentistId")
    private String dentistId;

    @JsonProperty(value = "patientId")
    private String patientId;

}
