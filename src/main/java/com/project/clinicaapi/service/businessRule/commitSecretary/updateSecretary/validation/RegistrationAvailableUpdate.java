package com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation;

import com.project.clinicaapi.service.businessRule.commitSecretary.RegistrationAvailable;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class RegistrationAvailableUpdate implements UpdateSecretaryVerification {

    @Override
    public void verification(UpdateSecretaryArgs args) {

        String registration = args.secretaryDTO().getRegistration();

        if (registration != null && !registration.equals(args.secretary().getRegistration())) {
            RegistrationAvailable.verification(args.secretaryRepository(), registration);
        }

    }

}
