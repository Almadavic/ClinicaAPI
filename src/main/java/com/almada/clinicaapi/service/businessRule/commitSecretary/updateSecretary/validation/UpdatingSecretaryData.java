package com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation;

import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
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
