package com.project.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.project.clinicaapi.service.businessRule.commitUser.EmailFormat;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class EmailFormatUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        String email = args.userDTO().getEmail();

        if (email != null) {
            EmailFormat.verification(email);
        }

    }

}
