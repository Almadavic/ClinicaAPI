package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.CellphoneAlreadyRegisteredException;

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
