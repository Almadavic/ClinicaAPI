package com.project.clinicaapi.service.businessRule.commitPatient;

import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.service.customException.CpfAlreadyRegisteredException;
import com.project.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;

import java.util.Optional;

public class CommitPatientValidations {

    private CommitPatientValidations() {

    }

    public static void findPatientByCpfValidation(PatientRepository patientRepository, String cpf) {

        Optional<Patient> patientOptional = patientRepository.findByCpf(cpf);

        if (patientOptional.isPresent()) {
            throw new CpfAlreadyRegisteredException(cpf);
        }

    }

}
