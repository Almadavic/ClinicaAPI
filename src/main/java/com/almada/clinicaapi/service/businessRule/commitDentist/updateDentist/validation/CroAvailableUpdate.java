package com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.almada.clinicaapi.service.businessRule.commitDentist.CroAvailable;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class CroAvailableUpdate implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        String cro = args.dentistDTO().getCro();

        if (cro != null && !cro.equals(args.dentist().getCro())) {
            CroAvailable.verification(args.dentistRepository(), cro);
        }

    }

}
