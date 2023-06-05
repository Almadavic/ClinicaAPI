package com.project.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
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

        CommitUserValidations.findUserByLoginValidation(userRepository, args.userDTO().getLogin());

    }

}
