package com.almada.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitUser.LoginAvailable;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class LoginAvailableRegister implements RegisterUserVerification {

    private final UserRepository userRepository;

    @Override
    public void verification(RegisterUserArgs args) {

        LoginAvailable.verification(userRepository, args.userDTO().getLogin());

    }

}
