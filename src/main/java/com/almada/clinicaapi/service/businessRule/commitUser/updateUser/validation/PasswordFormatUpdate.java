package com.almada.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.PasswordFormat;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 9)
@Component
public class PasswordFormatUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        PasswordFormat.verification(args.userDTO().getPassword());

    }

}
