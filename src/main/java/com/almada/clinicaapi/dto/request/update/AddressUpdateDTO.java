package com.almada.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@JsonPropertyOrder(value = {"country", "state", "city"})
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class AddressUpdateDTO {

    @JsonProperty(value = "country")
    private String country;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "city")
    private String city;

}
