package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.Patient;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder(value = {"id", "name"})
@Getter
public class AppointmentPatientResponseDTO extends RepresentationModel<AppointmentResponseDTO> {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "name")
    private final String name;

    public AppointmentPatientResponseDTO(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
    }

}
