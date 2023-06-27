package com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.validation;

import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitPatient.CpfAvailable;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class CpfAvailableUpdate implements UpdatePatientVerification {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void verification(UpdatePatientArgs args) {

        String cpf = args.patientDTO().getCpf();

        if(cpf != null && !cpf.equals(args.patient().getCpf())) {
            CpfAvailable.verification(args.patientRepository(), cpf);
        }

    }

}
