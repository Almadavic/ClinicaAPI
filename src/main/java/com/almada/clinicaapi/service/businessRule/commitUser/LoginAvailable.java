package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.LoginAlreadyRegisteredException;

import java.util.Optional;

public class LoginAvailable {

    private LoginAvailable() {

    }

    public static void verification(UserRepository userRepository, String login) {

        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isPresent()) {
            throw new LoginAlreadyRegisteredException(login);
        }

    }

}
