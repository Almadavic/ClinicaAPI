package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.customException.InvalidInstanceException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
