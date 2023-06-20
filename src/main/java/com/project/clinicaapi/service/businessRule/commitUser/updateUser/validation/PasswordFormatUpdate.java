package com.project.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 9)
@Component
public class PasswordFormatUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        CommitUserValidations.passwordFormatValidation(args.userDTO().getPassword());

    }

}
