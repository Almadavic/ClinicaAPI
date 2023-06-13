package com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.validation;

import com.project.clinicaapi.service.businessRule.commitPatient.CommitPatientValidations;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class CpfFormatRegister implements RegisterPatientVerification {

    @Override
    public void verification(RegisterPatientArgs args) {

        CommitPatientValidations.cpfFormatValidation(args.patientDTO().getCpf());

    }

}
