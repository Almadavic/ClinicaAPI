package com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import com.project.clinicaapi.util.VerifyAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(value = 1)
@Component
public class NoFieldFilledUpdateSecretary implements UpdateSecretaryVerification {

    @Override
    public void verification(UpdateSecretaryArgs args) {

        SecretaryUpdateDTO secretaryDTO = args.secretaryDTO();

        AddressUpdateDTO addressDTO = secretaryDTO.getAddress();

        List<Object> attributes = Arrays.asList(secretaryDTO.getLogin(), secretaryDTO.getPassword(), secretaryDTO.getEmail(), secretaryDTO.getName(),
                secretaryDTO.getCellphone(), secretaryDTO.getRegistration(), secretaryDTO.getGender(), secretaryDTO.getPasswordConfirmation());

        if ((VerifyAttributes.allNAttributesNull(attributes) && addressDTO == null) ||
                (VerifyAttributes.addressFieldsNullToo(addressDTO, attributes))) {
            throw new NoFieldFilledException();
        }

    }

}


