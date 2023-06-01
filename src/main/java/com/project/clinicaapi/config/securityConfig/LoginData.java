package com.project.clinicaapi.config.securityConfig;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@AllArgsConstructor
@Getter
@JsonPropertyOrder(value = {"email", "password"})
public class LoginData {

    @NotBlank
    @JsonProperty(value = "email")
    private String email;

    @NotBlank
    @JsonProperty(value = "password")
    private String password;

    public UsernamePasswordAuthenticationToken toConvert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
