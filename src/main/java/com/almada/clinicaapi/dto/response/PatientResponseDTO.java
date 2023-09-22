package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.customException.InvalidInstanceException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
