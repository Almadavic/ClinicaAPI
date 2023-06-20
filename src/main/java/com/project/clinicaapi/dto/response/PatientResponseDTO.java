package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.customException.InvalidInstanceException;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "gender", "cpf", "address"})
public class PatientResponseDTO extends UserResponseDTO{

    @JsonProperty(value = "cpf")
    private final String cpf;

    public PatientResponseDTO(User user) {
        super(user);
        Patient patient = verifyInstance(user);
        this.cpf = patient.getCpf();
    }

    private Patient verifyInstance(User user) {

        if(!(user instanceof Patient)) {
            throw new InvalidInstanceException("patient");
        }
        return (Patient) user;
    }

}
