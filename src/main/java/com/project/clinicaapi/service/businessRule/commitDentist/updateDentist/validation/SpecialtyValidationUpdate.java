package com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 4)
@Component
public class SpecialtyValidationUpdate implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        String specialty = args.dentistDTO().getSpeciality();

        if(specialty != null) {
            CommitDentistValidations.specialtyValueValidation(specialty);
        }

    }

}
