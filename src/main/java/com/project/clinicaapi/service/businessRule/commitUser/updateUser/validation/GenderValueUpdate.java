package com.project.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.project.clinicaapi.service.businessRule.commitUser.GenderValue;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 10)
@Component
public class GenderValueUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        String gender = args.userDTO().getGender();

        if (gender != null) {
            GenderValue.verification(gender);
        }

    }

}
