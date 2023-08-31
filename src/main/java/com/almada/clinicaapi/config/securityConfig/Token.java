package com.almada.clinicaapi.config.securityConfig;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"idusuario", "token", "type"})
public record Token(

        @JsonProperty(value = "token")
        String token,

        @JsonProperty(value = "type")
        String type

) {
}

