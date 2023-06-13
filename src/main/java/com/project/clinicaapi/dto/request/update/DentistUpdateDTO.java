package com.project.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "gender", "cro", "speciality", "workdays", "address"})
@NoArgsConstructor
@Getter
public class DentistUpdateDTO extends UserUpdateDTO{

    @JsonProperty(value = "cro")
    @Size(min = 4, max = 6)
    private String cro;

    @JsonProperty(value = "speciality")
    private String speciality;

    @JsonProperty(value = "workdays")
    @Size(max = 6)
    private List<Integer> workDays;

}
