package com.project.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@JsonPropertyOrder(value = {"country", "state", "city"})
@Getter
public class AddessRegisterDTO {

    @JsonProperty(value = "country")
    @NotBlank
    private String country;

    @JsonProperty(value = "state")
    @NotBlank
    private String state;

    @JsonProperty(value = "city")
    @NotBlank
    private String city;

}
