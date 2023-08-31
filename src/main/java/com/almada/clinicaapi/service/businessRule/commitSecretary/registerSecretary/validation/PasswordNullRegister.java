package com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.validation;

import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.PasswordNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class PasswordNullRegister implements RegisterSecretaryVerification {

    @Override
    public void verification(RegisterSecretaryArgs args) {

        SecretaryRegisterDTO secretaryDTO = args.secretaryDTO();

        PasswordNull.verification(secretaryDTO.getPassword(), secretaryDTO.getPasswordConfirmation());

    }

}
