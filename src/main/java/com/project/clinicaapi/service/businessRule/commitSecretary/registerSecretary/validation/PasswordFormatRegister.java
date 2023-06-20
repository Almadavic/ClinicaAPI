package com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.validation;

import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class PasswordFormatRegister implements RegisterSecretaryVerification {

    @Override
    public void verification(RegisterSecretaryArgs args) {

        CommitUserValidations.passwordFormatValidation(args.secretaryDTO().getPassword());

    }

}
