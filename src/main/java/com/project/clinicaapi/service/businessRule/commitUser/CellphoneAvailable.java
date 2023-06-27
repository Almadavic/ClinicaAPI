package com.project.clinicaapi.service.businessRule.commitUser;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.customException.CellphoneAlreadyRegisteredException;

import java.util.Optional;

public class CellphoneAvailable {

    private CellphoneAvailable() {

    }

    public static void verification(UserRepository userRepository, String cellphone) {

        Optional<User> userOptional = userRepository.findByCellphone(cellphone);

        if (userOptional.isPresent()) {
            throw new CellphoneAlreadyRegisteredException(cellphone);
        }

    }

}
