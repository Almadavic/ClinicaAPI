package com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.validation;

import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import com.project.clinicaapi.service.businessRule.commitUser.PasswordMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class PasswordMatchRegister implements RegisterSecretaryVerification {

    @Override
    public void verification(RegisterSecretaryArgs args) {

        SecretaryRegisterDTO secretaryDTO = args.secretaryDTO();

        PasswordMatch.verification(secretaryDTO.getPassword(), secretaryDTO.getPasswordConfirmation());

    }

}
