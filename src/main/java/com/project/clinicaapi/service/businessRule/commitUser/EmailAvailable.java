package com.project.clinicaapi.service.businessRule.commitUser;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.EmailAlreadyRegisteredException;

import java.util.Optional;

public class EmailAvailable {

    private EmailAvailable() {

    }

    public static void verification(UserRepository userRepository, String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new EmailAlreadyRegisteredException(email);
        }

    }

}
