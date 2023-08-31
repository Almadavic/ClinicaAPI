package com.almada.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitUser.LoginAvailable;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class LoginAvailableUpdate implements UpdateUserVerification {

    private final UserRepository userRepository;

    @Override
    public void verification(UpdateUserArgs args) {

        String login = args.userDTO().getLogin();

        if (login != null && !login.equals(args.user().getUsername())) {
            LoginAvailable.verification(userRepository, login);
        }

    }

}
