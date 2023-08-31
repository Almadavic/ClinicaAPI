package com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.validation;

import com.almada.clinicaapi.service.businessRule.commitSecretary.RegistrationAvailable;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 4)
@Component
public class RegistrationAvailableRegister implements RegisterSecretaryVerification {

    @Override
    public void verification(RegisterSecretaryArgs args) {

        RegistrationAvailable.verification(args.secretaryRepository(), args.secretaryDTO().getRegistration());

    }

}
