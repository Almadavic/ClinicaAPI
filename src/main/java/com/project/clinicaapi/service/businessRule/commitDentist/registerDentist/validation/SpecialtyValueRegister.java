package com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.project.clinicaapi.service.businessRule.commitDentist.SpecialtyValue;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 9)
@Component
public class SpecialtyValueRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        SpecialtyValue.verification(args.dentistDTO().getSpeciality());

    }

}
