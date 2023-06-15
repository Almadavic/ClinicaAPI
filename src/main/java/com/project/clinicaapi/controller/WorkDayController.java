package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.WorkDaySwagger;
import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import com.project.clinicaapi.service.serviceAction.WorkDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/workdays")
@RequiredArgsConstructor
public class WorkDayController implements WorkDaySwagger {

    private final WorkDayService workDayService;

    @Override
    @GetMapping
    public ResponseEntity<List<WorkDayResponseDTO>> findAll() {
        return ResponseEntity.ok().body(workDayService.findAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkDayResponseDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(workDayService.findById(id));
    }

}
