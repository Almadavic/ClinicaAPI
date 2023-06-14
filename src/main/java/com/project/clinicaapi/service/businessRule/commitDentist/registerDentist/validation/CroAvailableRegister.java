package com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.validation;

import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import com.project.clinicaapi.service.businessRule.commitPatient.CommitPatientValidations;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
@RequiredArgsConstructor
public class CroAvailableRegister implements RegisterDentistVerification {

    private final DentistRepository dentistRepository;

    @Override
    public void verification(RegisterDentistArgs args) {

        CommitDentistValidations.findDentistByCroValidation(dentistRepository, args.dentistDTO().getCro());

    }

}
