package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Dentist;

@JsonPropertyOrder(value = {"id", "name"})
public class AppointmentDentistResponseDTO {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "name")
    private final String name;

    public AppointmentDentistResponseDTO(Dentist dentist) {
        this.id = dentist.getId();
        this.name = dentist.getName();
    }

}
