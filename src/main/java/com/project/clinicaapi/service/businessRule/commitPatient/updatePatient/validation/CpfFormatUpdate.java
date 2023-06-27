package com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.validation;

import com.project.clinicaapi.service.businessRule.commitPatient.CpfFormat;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class CpfFormatUpdate implements UpdatePatientVerification {

    @Override
    public void verification(UpdatePatientArgs args) {

        String cpf = args.patientDTO().getCpf();

        if(cpf != null) {
            CpfFormat.verification(cpf);
        }

    }

}
