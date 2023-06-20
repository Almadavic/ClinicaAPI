package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.customException.InvalidInstanceException;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"id", "login", "email", "name", "cellphone", "gender", "registration", "address"})
public class SecretaryResponseDTO extends UserResponseDTO {

    @JsonProperty(value = "registration")
    private final String registration;

    public SecretaryResponseDTO(User user) {
        super(user);
        Secretary secretary = verifyInstance(user);
        this.registration = secretary.getRegistration();
    }

    private Secretary verifyInstance(User user) {

        if(!(user instanceof Secretary)) {
            throw new InvalidInstanceException("secretary");
        }
        return (Secretary) user;
    }

}
