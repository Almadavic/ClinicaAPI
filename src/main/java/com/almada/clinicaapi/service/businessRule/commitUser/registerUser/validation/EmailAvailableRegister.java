package com.almada.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitUser.EmailAvailable;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
@RequiredArgsConstructor
public class EmailAvailableRegister implements RegisterUserVerification {

    private final UserRepository userRepository;

    @Override
    public void verification(RegisterUserArgs args) {

        EmailAvailable.verification(userRepository, args.userDTO().getEmail());

    }

}
