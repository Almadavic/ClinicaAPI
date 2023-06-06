package com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.validation;

import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.service.businessRule.commitSecretary.CommitSecretaryValidations;
import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
@AllArgsConstructor
public class RegistrationAvailableRegister implements RegisterSecretaryVerification {

    private final SecretaryRepository secretaryRepository;

    @Override
    public void verification(RegisterSecretaryArgs args) {

        CommitSecretaryValidations.findSecretaryByRegistrationValidation(secretaryRepository, args.secretaryDTO().getRegistration());

    }

}
