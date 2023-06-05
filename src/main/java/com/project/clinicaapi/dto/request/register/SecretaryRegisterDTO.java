package com.project.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "gender", "registration", "address"})
@Getter
public class SecretaryRegisterDTO extends UserRegisterDTO{

    @JsonProperty(value = "registration")
    @NotBlank
    private String registration;

}
