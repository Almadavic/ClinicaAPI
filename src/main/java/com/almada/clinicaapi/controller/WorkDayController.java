package com.almada.clinicaapi.controller;

import com.almada.clinicaapi.dto.response.WorkDayResponseDTO;
import com.almada.clinicaapi.service.serviceAction.WorkDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workdays")
@RequiredArgsConstructor
public class WorkDayController  {

    private final WorkDayService workDayService;

    @GetMapping
    public ResponseEntity<List<WorkDayResponseDTO>> findAll() {
        return ResponseEntity.ok().body(workDayService.findAll());
    }

    @GetMapping(value = "/{index}")
    public ResponseEntity<WorkDayResponseDTO> findByIndex(@PathVariable(value = "id") Integer index) {
        return ResponseEntity.ok().body(workDayService.findByIndex(index));
    }

}
