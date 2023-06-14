package com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class WorkDayListRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        CommitDentistValidations.workdayListValidation(args.dentistDTO().getWorkDays());

    }

}
