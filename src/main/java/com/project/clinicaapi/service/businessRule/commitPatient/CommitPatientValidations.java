package com.project.clinicaapi.service.businessRule.commitPatient;

import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.service.customException.CpfAlreadyRegisteredException;
import com.project.clinicaapi.service.customException.InvalidCpfFormatException;
import com.project.clinicaapi.service.customException.InvalidNameFormatException;
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

    public static void cpfFormatValidation(String cpf) {

        String regexCpfValid = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

        if (!cpf.matches(regexCpfValid)) {
            throw new InvalidCpfFormatException(cpf);
        }

    }

}
