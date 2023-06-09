package com.project.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.project.clinicaapi.service.businessRule.commitUser.EmailFormat;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class EmailFormatRegister implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        EmailFormat.verification(args.userDTO().getEmail());

    }

}
