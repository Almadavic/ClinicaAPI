package com.almada.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "passwordConfirmation", "gender", "registration", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class SecretaryRegisterDTO extends UserRegisterDTO {

    @JsonProperty(value = "password")
    @Size(min = 6)
    private String password;

    @JsonProperty(value = "passwordConfirmation")
    private String passwordConfirmation;

    @JsonProperty(value = "registration")
    @NotBlank
    private String registration;



}
