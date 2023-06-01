package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.LogSwagger;
import com.project.clinicaapi.dto.response.LogResponseDTO;
import com.project.clinicaapi.service.serviceAction.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logs")
@RequiredArgsConstructor
public class LogController implements LogSwagger {

    private final LogService logService;

    @Override
    @GetMapping
    public ResponseEntity<Page<LogResponseDTO>> findPage(@PageableDefault(sort = "event_time", direction = Sort.Direction.ASC) Pageable pageable,
                                                         @RequestParam(value = "user", required = false) String user,
                                                         @RequestParam(value = "datestart", required = false) String dataStart,
                                                         @RequestParam(value = "dateend", required = false) String dataEnd) {

        return ResponseEntity.ok().body(logService.findPage(pageable, user, dataStart, dataEnd));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<LogResponseDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(logService.findById(id));
    }

}
