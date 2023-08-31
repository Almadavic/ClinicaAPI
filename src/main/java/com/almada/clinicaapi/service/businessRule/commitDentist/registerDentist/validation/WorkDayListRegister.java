package com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.almada.clinicaapi.service.businessRule.commitDentist.WorkDayList;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class WorkDayListRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        WorkDayList.verification(args.dentistDTO().getWorkDays());

    }

}
