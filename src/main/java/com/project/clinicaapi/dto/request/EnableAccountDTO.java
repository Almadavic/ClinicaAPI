package com.project.clinicaapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EnableAccountDTO {

    @JsonProperty(value = "code")
    @NotBlank
    @Size(min = 10, max = 10)
    private String code;

    @JsonProperty(value = "password")
    @NotBlank
    @Size(min = 6)
    private String password;

    @JsonProperty(value = "passwordConfirmation")
    @NotBlank
    private String passwordConfirmation;

}
