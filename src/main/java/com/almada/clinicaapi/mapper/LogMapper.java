package com.almada.clinicaapi.mapper;


import com.almada.clinicaapi.controller.LogController;
import com.almada.clinicaapi.dto.response.LogResponseDTO;
import com.almada.clinicaapi.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
        logDTO.add(linkTo(methodOn(LogController.class).findById(logDTO.getId())).withSelfRel());
        logDTO.add(linkTo(methodOn(LogController.class).findPage(null, logDTO.getUser(), null, null))
                .withRel(IanaLinkRelations.COLLECTION));
    }

}
