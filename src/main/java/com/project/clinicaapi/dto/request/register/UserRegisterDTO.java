package com.project.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "password", "gender", "address"})
@Getter
public abstract class UserRegisterDTO {

    @JsonProperty(value = "login")
    @Size(min = 4)
    @NotBlank
    private String login;

    @JsonProperty(value = "email")
    @Email
    @NotBlank
    private String email;

    @JsonProperty(value = "name")
    @Size(min = 4)
    @NotBlank
    private String name;

    @JsonProperty(value = "cellphone")
    @Size(min = 10)
    private String cellphone;

    @Column(name = "password")
    @Size(min = 6)
    private String password;

    @JsonProperty(value = "gender")
    @NotBlank
    private String gender;

    @JsonPropertyOrder(value = "address")
    @Valid
    private AddessRegisterDTO address;

}
