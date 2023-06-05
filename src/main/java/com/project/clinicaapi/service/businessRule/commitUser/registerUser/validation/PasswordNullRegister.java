package com.project.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.project.clinicaapi.dto.request.register.UserRegisterDTO;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 7)
@Component
public class PasswordNullRegister implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        UserRegisterDTO userDTO = args.userDTO();

        CommitUserValidations.passwordNull(userDTO.getPassword(), userDTO.getPasswordConfirmation());

    }

}
