package com.almada.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.GenderValue;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 9)
@Component
public class GenderValueRegister implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        GenderValue.verification(args.userDTO().getGender());

    }

}
