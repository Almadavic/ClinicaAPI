package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.User;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "enabled", "gender", "cpf", "role", "address"})
public class PatientResponseDTO extends UserResponseDTO{

    @JsonProperty(value = "cpf")
    private final String cpf;

    public PatientResponseDTO(User user) {
        super(user);
        Patient patient = (Patient) user;
        this.cpf = patient.getCpf();
    }

}
