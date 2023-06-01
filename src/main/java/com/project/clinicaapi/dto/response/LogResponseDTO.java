package com.project.clinicaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.clinicaapi.entity.Log;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = {"id", "eventTime", "user", "event"})
@Getter
public class LogResponseDTO {

    @JsonProperty(value = "id")
    private final Long id;

    @JsonProperty(value = "eventTime")
    private final LocalDateTime eventTime;

    @JsonProperty(value = "user")
    private final String user;

    @JsonProperty(value = "event")
    private final String event;

    public LogResponseDTO(Log log) {
        this.id = log.getId();
        this.eventTime = log.getEventTime();
        this.user = log.getUser();
        this.event = log.getEvent();
    }

}
