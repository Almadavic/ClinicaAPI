package com.project.clinicaapi.service.businessRule.commitDentist;

import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.customException.CroAlreadyRegisteredException;

import java.util.Optional;

public class CroAvailable {

    private CroAvailable() {

    }


    public static void verification(DentistRepository dentistRepository, String cro) {

        Optional<Dentist> dentistOptional = dentistRepository.findByCro(cro);

        if (dentistOptional.isPresent()) {
            throw new CroAlreadyRegisteredException(cro);
        }

    }

}
