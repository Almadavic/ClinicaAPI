package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.EmailAlreadyRegisteredException;

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
