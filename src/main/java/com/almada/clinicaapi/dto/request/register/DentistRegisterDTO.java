package com.almada.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "gender", "cro", "speciality", "workdays", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class DentistRegisterDTO extends UserRegisterDTO {

    @JsonProperty(value = "cro")
    @NotBlank
    @Size(min = 4, max = 6)
    private String cro;

    @JsonProperty(value = "speciality")
    @NotBlank
    private String speciality;

    @JsonProperty(value = "workdays")
    @Size(max = 6)
    @Setter
    private Set<Integer> workDays;

}
