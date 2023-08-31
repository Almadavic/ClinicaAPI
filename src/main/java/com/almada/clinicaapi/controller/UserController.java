package com.almada.clinicaapi.controller;

import com.almada.clinicaapi.dto.request.ChangePasswordDTO;
import com.almada.clinicaapi.dto.request.EnableAccountDTO;
import com.almada.clinicaapi.dto.response.UserMonitoringResponseDTO;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.service.serviceAction.ChangePasswordService;
import com.almada.clinicaapi.service.serviceAction.EnableAccountService;
import com.almada.clinicaapi.service.serviceAction.UserService;
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
public class UserController {

    private final UserService userService;

    private final EnableAccountService activeAccountService;

    private final ChangePasswordService changePasswordService;

    @GetMapping
    public ResponseEntity<Page<UserMonitoringResponseDTO>> findPage(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok().body(userService.findPage(pageable));
    }

    @GetMapping(value = "/{userid}")
    public ResponseEntity<UserMonitoringResponseDTO> findById(@PathVariable(value = "userid") String userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @PatchMapping(value = "/disable/{userId}")
    public ResponseEntity<Void> disableAccount(@PathVariable(value = "userId") String id,
                                               @AuthenticationPrincipal User userLogged) {

        userService.disableAccount(id, userLogged);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/enableaccount")
    public ResponseEntity<String> enableAccount(@RequestBody @Valid EnableAccountDTO enableAccountDTO) {

        activeAccountService.enableAccount(enableAccountDTO);
        return ResponseEntity.ok().body("Conta ativada com sucesso");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id, @AuthenticationPrincipal User userLogged) {
        userService.delete(id, userLogged);
        return ResponseEntity.ok().body("The user id: " + id + " was deleted from the system");
    }

    @GetMapping(value = "/forgetpassword/{email}")
    public ResponseEntity<String> forgetPassword(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok().body(userService.forgetPassword(email));
    }

    @PutMapping(value = "/changepassword")
    public ResponseEntity<String> changePasswordForgotten(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
        changePasswordService.changePassword(changePasswordDTO);
        return ResponseEntity.ok().body("Password has changed successfully!");
    }

}
