package com.project.clinicaapi.service.businessRule.commitSecretary;

import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.service.customException.*;

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
