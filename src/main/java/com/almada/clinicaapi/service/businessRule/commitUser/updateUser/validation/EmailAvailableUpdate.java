package com.almada.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitUser.EmailAvailable;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
@RequiredArgsConstructor
public class EmailAvailableUpdate implements UpdateUserVerification {

    private final UserRepository userRepository;

    @Override
    public void verification(UpdateUserArgs args) {

        String email = args.userDTO().getEmail();

        if (email != null && !email.equals(args.user().getEmail())) {
            EmailAvailable.verification(userRepository, email);
        }

    }

}
