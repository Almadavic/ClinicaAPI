package com.almada.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "passwordConfirmation", "gender", "cpf", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class PatientUpdateDTO extends UserUpdateDTO{

    @JsonProperty(value = "cpf")
    @Size(min = 14, max = 14)
    private String cpf;

}
