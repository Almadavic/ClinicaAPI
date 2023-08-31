package com.almada.clinicaapi.service.businessRule.commitPatient;

import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.service.customException.CpfAlreadyRegisteredException;

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
