package com.almada.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitUser.CellphoneAvailable;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 5)
@Component
@RequiredArgsConstructor
public class CellphoneAvailableRegister implements RegisterUserVerification {

    private final UserRepository userRepository;

    @Override
    public void verification(RegisterUserArgs args) {

        CellphoneAvailable.verification(userRepository, args.userDTO().getCellphone());

    }

}
