package com.almada.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "gender", "cpf", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class PatientRegisterDTO extends UserRegisterDTO {

    @JsonProperty(value = "cpf")
    @NotBlank
    @Size(min = 14, max = 14)
    private String cpf;

}
