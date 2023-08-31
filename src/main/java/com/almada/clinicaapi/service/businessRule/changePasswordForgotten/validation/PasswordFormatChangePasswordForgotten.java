package com.almada.clinicaapi.service.businessRule.changePasswordForgotten.validation;

import com.almada.clinicaapi.service.businessRule.changePasswordForgotten.ChangePasswordForgottenArgs;
import com.almada.clinicaapi.service.businessRule.changePasswordForgotten.ChangePasswordForgottenValidation;
import com.almada.clinicaapi.service.businessRule.commitUser.PasswordFormat;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class PasswordFormatChangePasswordForgotten implements ChangePasswordForgottenValidation {
    @Override
    public void verification(ChangePasswordForgottenArgs args) {

        PasswordFormat.verification(args.changePasswordDTO().getPassword());

    }
}
