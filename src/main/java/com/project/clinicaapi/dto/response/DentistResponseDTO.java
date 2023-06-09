package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.User;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "enabled", "gender", "role", "address"})
public class DentistResponseDTO extends UserResponseDTO{
    public DentistResponseDTO(User user) {
        super(user);
        Dentist dentist = (Dentist) user;
    }
}
