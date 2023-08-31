package com.almada.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.NameFormat;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 6)
@Component
public class NameFormatRegister implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        NameFormat.verification(args.userDTO().getName());

    }

}
