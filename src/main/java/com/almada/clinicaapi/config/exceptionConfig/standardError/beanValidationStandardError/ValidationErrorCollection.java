package com.almada.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@JsonPropertyOrder(value = {"timestamp", "status", "erro", "errosValidacao", "caminho"})
public class ValidationErrorCollection {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "GMT-3")
    @JsonProperty(value = "timestamp")
    private final Instant timestamp;

    @JsonProperty(value = "status")
    private final Integer status;

    @JsonProperty(value = "erro")
    private final String error;

    @JsonProperty(value = "caminho")
    private final String path;

    @JsonProperty(value = "errosValidacao")
    private final List<StandardErrorFieldsNotValid> validationErrorList = new ArrayList<>();

    public ValidationErrorCollection(Integer status, String path, String error) {
        this.timestamp = Instant.now();
        this.status = status;
        this.path = path;
        this.error = error;
    }

    public void addStandardErrorArgsNotValid(StandardErrorFieldsNotValid standard) {
        validationErrorList.add(standard);
    }

}
