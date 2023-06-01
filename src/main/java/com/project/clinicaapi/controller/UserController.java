package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.UserSwagger;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.service.serviceAction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    private final UserService userService;

    @Override
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findPage(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok().body(userService.findPage(pageable));
    }

    @Override
    @GetMapping(value = "/{userid}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable(value = "userid") String userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

}
