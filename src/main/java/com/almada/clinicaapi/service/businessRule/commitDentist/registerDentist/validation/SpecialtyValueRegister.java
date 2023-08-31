package com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.almada.clinicaapi.service.businessRule.commitDentist.SpecialtyValue;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
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
