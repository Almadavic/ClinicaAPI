package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.response.LogResponseDTO;
import com.project.clinicaapi.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class LogMapper {

    public LogResponseDTO toLogDTO(Log log) {
        return new LogResponseDTO(log);
    }

    public Page<LogResponseDTO> toLogDTOPage(Page<Log> logs) {
        return logs.map(LogResponseDTO::new);
    }

}
