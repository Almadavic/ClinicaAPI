package com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.validation;

import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
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
