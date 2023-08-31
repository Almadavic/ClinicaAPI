package com.almada.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "passwordConfirmation", "gender", "cro", "speciality", "workdays", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class DentistUpdateDTO extends UserUpdateDTO{

    @JsonProperty(value = "cro")
    @Size(min = 4, max = 6)
    private String cro;

    @JsonProperty(value = "speciality")
    private String speciality;

    @JsonProperty(value = "workdays")
    @Size(max = 6)
    private Set<Integer> workDays;

}
