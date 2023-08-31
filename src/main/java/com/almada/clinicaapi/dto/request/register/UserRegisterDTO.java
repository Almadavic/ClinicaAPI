package com.almada.clinicaapi.dto.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@JsonPropertyOrder(value = {"login", "email", "name", "cellphone", "gender", "address"})
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
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

    @JsonProperty(value = "gender")
    @NotBlank
    private String gender;

    @JsonPropertyOrder(value = "address")
    @Valid
    private AddessRegisterDTO address;

}
