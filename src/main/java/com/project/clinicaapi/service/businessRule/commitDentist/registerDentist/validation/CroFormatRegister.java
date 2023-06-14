package com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import com.project.clinicaapi.service.businessRule.commitPatient.CommitPatientValidations;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class CroFormatRegister implements RegisterDentistVerification {

    @Override
    public void verification(RegisterDentistArgs args) {

        CommitDentistValidations.croFormatValidation(args.dentistDTO().getCro());

    }

}
