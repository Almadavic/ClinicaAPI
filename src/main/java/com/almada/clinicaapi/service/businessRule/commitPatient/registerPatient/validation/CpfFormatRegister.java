package com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.validation;

import com.almada.clinicaapi.service.businessRule.commitPatient.CpfFormat;
import com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class CpfFormatRegister implements RegisterPatientVerification {

    @Override
    public void verification(RegisterPatientArgs args) {

        CpfFormat.verification(args.patientDTO().getCpf());

    }

}
