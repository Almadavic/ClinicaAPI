package com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Order(value = 5)
@Component
public class WorkDayListUpdate implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        Set<Long> workDays = args.dentistDTO().getWorkDays();

        if (workDays != null) {
            CommitDentistValidations.workdayListValidation(workDays);
        }

    }

}
