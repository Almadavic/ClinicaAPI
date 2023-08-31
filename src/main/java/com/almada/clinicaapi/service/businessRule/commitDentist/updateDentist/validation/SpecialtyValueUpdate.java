package com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.almada.clinicaapi.service.businessRule.commitDentist.SpecialtyValue;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 4)
@Component
public class SpecialtyValueUpdate implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        String specialty = args.dentistDTO().getSpeciality();

            SpecialtyValue.verification(specialty);

    }

}
