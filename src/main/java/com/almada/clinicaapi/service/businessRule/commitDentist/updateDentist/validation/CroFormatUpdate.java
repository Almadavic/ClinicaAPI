package com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.almada.clinicaapi.service.businessRule.commitDentist.CroFormat;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class CroFormatUpdate implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        String cro = args.dentistDTO().getCro();

        if (cro != null) {
            CroFormat.verification(args.dentistDTO().getCro());
        }

    }

}
