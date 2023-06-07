package com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation;

import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
@RequiredArgsConstructor
public class UpdatingSecretaryData implements UpdateSecretaryVerification {

    @Override
    public void verification(UpdateSecretaryArgs args) {

        SecretaryUpdateDTO secretaryDTO = args.secretaryDTO();

        Secretary secretary = args.secretary();

        setRegistration(secretaryDTO, secretary);

    }

    private void setRegistration(SecretaryUpdateDTO secretaryDTO, Secretary secretary) {
        String registration = secretaryDTO.getRegistration();
        if (registration != null) {
            secretary.setRegistration(registration);
        }
    }

}
