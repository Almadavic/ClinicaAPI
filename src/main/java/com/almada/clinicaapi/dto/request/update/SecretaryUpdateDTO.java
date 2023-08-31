package com.almada.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "passwordConfirmation", "gender", "registration", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class SecretaryUpdateDTO extends UserUpdateDTO {

    @JsonProperty(value = "registration")
    private String registration;

}
