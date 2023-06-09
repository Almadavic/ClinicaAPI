package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.User;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "enabled", "gender", "role", "address"})
public class PatientResponseDTO extends UserResponseDTO{
    public PatientResponseDTO(User user) {
        super(user);
        Patient patient = (Patient) user;
    }

}
