package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.Dentist;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder(value = {"id", "name"})
@Getter
public class AppointmentDentistResponseDTO extends RepresentationModel<AppointmentDentistResponseDTO> {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "name")
    private final String name;

    public AppointmentDentistResponseDTO(Dentist dentist) {
        this.id = dentist.getId();
        this.name = dentist.getName();
    }

}
