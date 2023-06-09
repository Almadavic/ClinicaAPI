package com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.validation;

import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitPatient.CommitPatientValidations;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
@RequiredArgsConstructor
public class CpfAvailableUpdate implements UpdatePatientVerification {

    private final PatientRepository patientRepository;

    @Override
    public void verification(UpdatePatientArgs args) {

        CommitPatientValidations.findPatientByCpfValidation(patientRepository, args.patientDTO().getCpf());

    }

}
