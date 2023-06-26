package com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Order(value = 9)
@Component
public class SpecialtyValidationRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        CommitDentistValidations.specialtyValueValidation(args.dentistDTO().getSpeciality());

    }

}
