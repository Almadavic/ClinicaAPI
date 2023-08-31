package com.almada.clinicaapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
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
