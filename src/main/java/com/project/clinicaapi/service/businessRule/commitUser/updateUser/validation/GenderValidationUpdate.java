package com.project.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 9)
@Component
public class GenderValidationUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        String gender = args.userDTO().getGender();

        if (gender != null) {
            CommitUserValidations.genderValueValidation(gender);
        }

    }

}
