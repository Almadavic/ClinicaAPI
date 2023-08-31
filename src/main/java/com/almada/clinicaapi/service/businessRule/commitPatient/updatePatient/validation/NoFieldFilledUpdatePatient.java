package com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.validation;

import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
import com.almada.clinicaapi.service.serviceAction.AttributesListToUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class NoFieldFilledUpdatePatient implements UpdatePatientVerification {

    private final AttributesListToUpdateService attributesListToUpdateService;

    @Override
    public void verification(UpdatePatientArgs args) {

        PatientUpdateDTO patientDTO = args.patientDTO();

        List<Object> attributes = attributesListToUpdateService.getAttributesGenericsUser(patientDTO);
        attributes.addAll(Arrays.asList(patientDTO.getCpf()));

        if (attributesListToUpdateService.allAttributesNull(attributes)) {
            throw new NoFieldFilledException();
        }

    }

}
