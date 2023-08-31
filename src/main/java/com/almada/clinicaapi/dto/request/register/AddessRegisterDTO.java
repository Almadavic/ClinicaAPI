package com.almada.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@JsonPropertyOrder(value = {"country", "state", "city"})
@NoArgsConstructor
@AllArgsConstructor
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
