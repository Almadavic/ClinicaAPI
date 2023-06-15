package com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import com.project.clinicaapi.util.VerifyAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Order(value = 1)
@Component
public class NoFieldFilledUpdateDentist implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        DentistUpdateDTO dentistDTO = args.dentistDTO();

        List<Object> attributes = new ArrayList<>(Arrays.asList(dentistDTO.getLogin(), dentistDTO.getPassword(), dentistDTO.getEmail(),
                dentistDTO.getName(), dentistDTO.getCellphone(), dentistDTO.getCro(), dentistDTO.getSpeciality(), dentistDTO.getGender(),
                dentistDTO.getPasswordConfirmation()));

        CommitUserValidations.addAddressAttributesToList(attributes, dentistDTO.getAddress());

        if (commonAttributesAndWorkDayNull(attributes, dentistDTO) || workDayNotNullButListEmpty(attributes, dentistDTO)) {
            throw new NoFieldFilledException();
        }

    }

    private boolean commonAttributesAndWorkDayNull(List<Object> attributes, DentistUpdateDTO dentistDTO) {
        return VerifyAttributes.allAttributesNull(attributes) && dentistDTO.getWorkDays() == null;
    }

    private boolean workDayNotNullButListEmpty(List<Object> attributes, DentistUpdateDTO dentistDTO) {
        Set<Long> workDays = dentistDTO.getWorkDays();
        return VerifyAttributes.allAttributesNull(attributes) && workDays != null && workDays.isEmpty();
    }

}
