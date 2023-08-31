package com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
import com.almada.clinicaapi.service.serviceAction.AttributesListToUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class NoFieldFilledUpdateDentist implements UpdateDentistVerification {

    private final AttributesListToUpdateService attributesListToUpdateService;

    @Override
    public void verification(UpdateDentistArgs args) {

        DentistUpdateDTO dentistDTO = args.dentistDTO();

        List<Object> attributes = attributesListToUpdateService.getAttributesGenericsUser(dentistDTO);
        attributes.addAll(Arrays.asList(dentistDTO.getCro(), dentistDTO.getSpeciality()));

        if (commonAttributesAndWorkDayNull(attributes, dentistDTO) || workDayNotNullButListEmpty(attributes, dentistDTO)) {
            throw new NoFieldFilledException();
        }

    }

    private boolean commonAttributesAndWorkDayNull(List<Object> attributes, DentistUpdateDTO dentistDTO) {
        return attributesListToUpdateService.allAttributesNull(attributes) && dentistDTO.getWorkDays() == null;
    }

    private boolean workDayNotNullButListEmpty(List<Object> attributes, DentistUpdateDTO dentistDTO) {
        Set<Integer> workDays = dentistDTO.getWorkDays();
        return attributesListToUpdateService.allAttributesNull(attributes) && workDays != null && workDays.isEmpty();
    }

}
