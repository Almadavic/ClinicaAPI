package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.service.customException.CroAlreadyRegisteredException;

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
