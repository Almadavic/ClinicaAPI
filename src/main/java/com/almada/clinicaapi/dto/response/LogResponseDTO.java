package com.almada.clinicaapi.dto.response;

import com.almada.clinicaapi.entity.Log;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = {"id", "eventTime", "user", "event"})
@Getter
public class LogResponseDTO extends RepresentationModel<LogResponseDTO> {

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
