package com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.almada.clinicaapi.service.businessRule.commitDentist.CroAvailable;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class CroAvailableRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        CroAvailable.verification(args.dentistRepository(), args.dentistDTO().getCro());

    }

}
