package com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.validation;

import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.PasswordFormat;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class PasswordFormatRegister implements RegisterSecretaryVerification {

    @Override
    public void verification(RegisterSecretaryArgs args) {

        PasswordFormat.verification(args.secretaryDTO().getPassword());

    }

}
