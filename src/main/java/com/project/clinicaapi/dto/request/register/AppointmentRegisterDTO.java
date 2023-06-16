package com.project.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder(value = {"procedure", "appointmentDate", "timeStart", "timeEnd", "dentistId", "patientId"})
public class AppointmentRegisterDTO {

    @JsonProperty(value = "procedure")
    @NotBlank
    private String procedure;

    @JsonProperty(value = "appointmentDate")
    @NotNull
    private LocalDate appointmentDate;

    @JsonProperty(value = "timeStart")
    @NotNull
    private LocalTime timeStart;

    @JsonProperty(value = "timeEnd")
    @NotNull
    private LocalTime timeEnd;

    @JsonProperty(value = "dentistId")
    @NotBlank
    private String dentistId;

    @JsonProperty(value = "patientId")
    @NotBlank
    private String patientId;

}
