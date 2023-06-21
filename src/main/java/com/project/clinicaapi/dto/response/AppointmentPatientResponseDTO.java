package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.Patient;

@JsonPropertyOrder(value = {"id", "name"})
public class AppointmentPatientResponseDTO {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "name")
    private final String name;

    public AppointmentPatientResponseDTO(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
    }

}
