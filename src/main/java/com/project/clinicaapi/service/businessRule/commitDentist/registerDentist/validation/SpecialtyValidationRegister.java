package com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 9)
@Component
public class SpecialtyValidationRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        CommitDentistValidations.specialtyValueValidation(args.dentistDTO().getSpeciality());

    }

}
