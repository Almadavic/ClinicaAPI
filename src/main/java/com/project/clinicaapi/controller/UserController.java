package com.project.clinicaapi.controller;

import com.project.clinicaapi.config.swaggerConfig.endPoint.UserSwagger;
import com.project.clinicaapi.dto.request.EnableAccountDTO;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.service.serviceAction.EnableAccountService;
import com.project.clinicaapi.service.serviceAction.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    private final UserService userService;

    private final EnableAccountService activeAccountService;

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

    @Override
    @PatchMapping(value = "/disable/{userid}")
    public ResponseEntity<Void> disableAccount(@PathVariable(value = "userid") String id,
                                               @AuthenticationPrincipal User userLogged) {

        userService.disableAccount(id, userLogged);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/enableaccount")
    public ResponseEntity<String> enableAccount(@RequestBody @Valid EnableAccountDTO enableAccountDTO) {

        activeAccountService.enableAccount(enableAccountDTO);
        return ResponseEntity.ok().body("Conta ativada com sucesso");
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id, @AuthenticationPrincipal User userLogged) {
        userService.delete(id, userLogged);
        return ResponseEntity.ok().body("The user id: " + id + " was deleted from the system");
    }

}
