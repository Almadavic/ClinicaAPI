package com.project.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitUser.CellphoneAvailable;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 5)
@Component
@RequiredArgsConstructor
public class CellphoneAvailableUpdate implements UpdateUserVerification {

    private final UserRepository userRepository;

    @Override
    public void verification(UpdateUserArgs args) {

        String cellphone = args.userDTO().getCellphone();

        if (cellphone != null && !cellphone.equals(args.user().getCellphone())) {
            CellphoneAvailable.verification(userRepository, cellphone);
        }

    }

}
