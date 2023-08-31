package com.almada.clinicaapi.mapper;


import com.almada.clinicaapi.dto.response.LogResponseDTO;
import com.almada.clinicaapi.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class LogMapper {

    public LogResponseDTO toLogDTO(Log log) {
        LogResponseDTO logDTO = new LogResponseDTO(log);
        addHateoas(logDTO);
        return logDTO;
    }

    public Page<LogResponseDTO> toLogDTOPage(Page<Log> logs) {
        Page<LogResponseDTO> logsDTO = logs.map(LogResponseDTO::new);
        logsDTO.forEach(this::addHateoas);
        return logsDTO;
    }

    private void addHateoas(LogResponseDTO logDTO) {

    }

}
