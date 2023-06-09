package com.project.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "passwordconfirmation", "gender", "registration", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class SecretaryRegisterDTO extends UserRegisterDTO {

    @JsonProperty(value = "registration")
    @NotBlank
    private String registration;

}
