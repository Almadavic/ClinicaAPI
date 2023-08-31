package com.almada.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder(value = {"campo", "mensagem"})
public class StandardErrorFieldsNotValid implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "campo")
    private final String field;

    @JsonProperty(value = "mensagem")
    private final String message;

    public StandardErrorFieldsNotValid(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
