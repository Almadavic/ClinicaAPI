package com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation;

import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import com.project.clinicaapi.service.serviceAction.AttributesListToUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class NoFieldFilledUpdateSecretary implements UpdateSecretaryVerification {

    private final AttributesListToUpdateService attributesListToUpdateService;

    @Override
    public void verification(UpdateSecretaryArgs args) {

        SecretaryUpdateDTO secretaryDTO = args.secretaryDTO();

        List<Object> attributes = attributesListToUpdateService.getAttributesGenericsUser(secretaryDTO);
        attributes.addAll(Arrays.asList(secretaryDTO.getRegistration()));

        if (attributesListToUpdateService.allAttributesNull(attributes)) {
            throw new NoFieldFilledException();
        }

    }

}


