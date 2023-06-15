package com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.validation;

import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitPatient.CommitPatientValidations;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class CpfAvailableRegister implements RegisterPatientVerification {

    @Override
    public void verification(RegisterPatientArgs args) {

        CommitPatientValidations.findPatientByCpfValidation(args.patientRepository(), args.patientDTO().getCpf());

    }

}
