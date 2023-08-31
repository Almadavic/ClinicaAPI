package com.almada.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder(value = {"procedure", "appointmentDate", "timeStart", "timeEnd", "dentistId", "patientId"})
@Builder
@Getter
public class AppointmentUpdateDTO {

    @JsonProperty(value = "procedure")
    private String procedure;

    @JsonProperty(value = "appointmentDate")
    private LocalDate appointmentDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT-3")
    @JsonProperty(value = "timeStart")
    private LocalTime timeStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT-3")
    @JsonProperty(value = "timeEnd")
    private LocalTime timeEnd;

}
