package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.dto.response.LogResponseDTO;
import com.almada.clinicaapi.entity.Log;
import org.springframework.stereotype.Component;

@Component
public class LogFactory {

    public Log entity() {
        return Log.builder()
                .event("event log")
                .user("user log")
                .build();
    }

    public LogResponseDTO dtoResponse() {
        return new LogResponseDTO(entity());
    }

}
