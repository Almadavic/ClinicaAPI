package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.vo.Address;
import lombok.Getter;

@JsonPropertyOrder(value = {"country", "state", "city"})
@Getter
public class AddressResponseDTO {

    @JsonProperty(value = "country")
    private final String country;

    @JsonProperty(value = "state")
    private final String state;

    @JsonProperty(value = "city")
    private final String city;

    public AddressResponseDTO(Address address) {
        this.country = address.getCountry();
        this.state = address.getState();
        this.city = address.getCity();
    }

}
