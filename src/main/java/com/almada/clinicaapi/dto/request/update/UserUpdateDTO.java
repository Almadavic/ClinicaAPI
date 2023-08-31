package com.almada.clinicaapi.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "passwordConfirmation", "gender", "address"})
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {

    @JsonProperty(value = "login")
    @Size(min = 4)
    private String login;

    @JsonProperty(value = "email")
    @Email
    private String email;

    @JsonProperty(value = "name")
    @Size(min = 4)
    private String name;

    @JsonProperty(value = "cellphone")
    @Size(min = 10)
    private String cellphone;

    @JsonProperty(value = "password")
    @Size(min = 6)
    private String password;

    @JsonProperty(value = "passwordConfirmation")
    private String passwordConfirmation;

    @JsonProperty(value = "gender")
    private String gender;

    @JsonPropertyOrder(value = "address")
    @Valid
    private AddressUpdateDTO address;

}
