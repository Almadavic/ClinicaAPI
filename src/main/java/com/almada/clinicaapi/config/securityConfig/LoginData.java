package com.almada.clinicaapi.config.securityConfig;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@AllArgsConstructor
@Getter
@JsonPropertyOrder(value = {"login", "password"})
public class LoginData {

    @NotBlank
    @JsonProperty(value = "login")
    private String login;

    @NotBlank
    @JsonProperty(value = "password")
    private String password;

    public UsernamePasswordAuthenticationToken toConvert() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }
}
