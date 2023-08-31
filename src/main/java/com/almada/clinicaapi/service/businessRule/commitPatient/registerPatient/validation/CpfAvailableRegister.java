package com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.validation;

import com.almada.clinicaapi.service.businessRule.commitPatient.CpfAvailable;
import com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class CpfAvailableRegister implements RegisterPatientVerification {

    @Override
    public void verification(RegisterPatientArgs args) {

        CpfAvailable.verification(args.patientRepository(), args.patientDTO().getCpf());

    }

}
