package com.project.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder(value = {"procedure", "appointmentDate", "timeStart", "timeEnd", "dentistId", "patientId"})
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

    @JsonProperty(value = "dentistId")
    private String dentistId;

    @JsonProperty(value = "patientId")
    private String patientId;

}
