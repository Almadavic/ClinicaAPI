package com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.almada.clinicaapi.service.businessRule.commitDentist.CroFormat;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class CroFormatRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        CroFormat.verification(args.dentistDTO().getCro());

    }

}
