package com.project.clinicaapi.service.businessRule.commitSecretary;

import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;

import java.util.Arrays;
import java.util.Optional;

public class CommitSecretaryValidations {

    private CommitSecretaryValidations() {

    }

    public static void findSecretaryByRegistrationValidation(SecretaryRepository secretaryRepository, String registration) {

        Optional<Secretary> secretaryOptional = secretaryRepository.findByRegistration(registration);

        if (secretaryOptional.isPresent()) {
            throw new RegistrationAlreadyRegisteredException(registration);
        }

    }

}
