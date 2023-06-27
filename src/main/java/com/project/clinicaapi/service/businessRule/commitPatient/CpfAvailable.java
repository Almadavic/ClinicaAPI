package com.project.clinicaapi.service.businessRule.commitPatient;

import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.customException.CpfAlreadyRegisteredException;

import java.util.Optional;

public class CpfAvailable {

    private CpfAvailable() {

    }

    public static void verification(PatientRepository patientRepository, String cpf) {

        Optional<Patient> patientOptional = patientRepository.findByCpf(cpf);

        if (patientOptional.isPresent()) {
            throw new CpfAlreadyRegisteredException(cpf);
        }

    }

}
