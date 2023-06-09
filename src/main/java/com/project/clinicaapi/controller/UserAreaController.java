package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.UserAreaSwagger;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.serviceAction.UserAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userarea")
@RequiredArgsConstructor
public class UserAreaController implements UserAreaSwagger {

    private final UserAreaService userAreaService;

    @GetMapping(value = "/myprofile")
    public ResponseEntity<UserResponseDTO> myProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userAreaService.myProfile(user));
    }

}
