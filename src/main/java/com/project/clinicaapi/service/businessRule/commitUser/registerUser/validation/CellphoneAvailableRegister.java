package com.project.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 5)
@Component
@RequiredArgsConstructor
public class CellphoneAvailableRegister implements RegisterUserVerification {

    private final UserRepository userRepository;

    @Override
    public void verification(RegisterUserArgs args) {

        CommitUserValidations.findUserByCellphoneValidation(userRepository, args.userDTO().getCellphone());

    }

}
