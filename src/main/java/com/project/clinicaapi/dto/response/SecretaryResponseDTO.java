package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;

@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "enabled", "gender", "registration", "role", "address"})
public class SecretaryResponseDTO extends UserResponseDTO{

    @JsonProperty(value = "registration")
    private String registration;

    public SecretaryResponseDTO(User user) {
        super(user);
        Secretary secretary = (Secretary) user;
        this.registration = secretary.getRegistration();
    }

}
