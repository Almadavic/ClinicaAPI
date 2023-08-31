package com.almada.clinicaapi.service.businessRule.commitSecretary;

import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.repository.SecretaryRepository;
import com.almada.clinicaapi.service.customException.*;

import java.util.Optional;

public class RegistrationAvailable {

    private RegistrationAvailable() {

    }

    public static void verification(SecretaryRepository secretaryRepository, String registration) {

        Optional<Secretary> secretaryOptional = secretaryRepository.findByRegistration(registration);

        if (secretaryOptional.isPresent()) {
            throw new RegistrationAlreadyRegisteredException(registration);
        }

    }

}
