package com.project.clinicaapi.service.businessRule.commitUser;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.LoginAlreadyRegisteredException;

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
