package com.almada.clinicaapi.service.businessRule.forgetPassword.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.EmailFormat;
import com.almada.clinicaapi.service.businessRule.forgetPassword.ForgetPasswordArgs;
import com.almada.clinicaapi.service.businessRule.forgetPassword.ForgetPasswordVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class EmailFormatToRecoverPassword implements ForgetPasswordVerification {

    @Override
    public void verification(ForgetPasswordArgs args) {

        EmailFormat.verification(args.email());

    }

}
