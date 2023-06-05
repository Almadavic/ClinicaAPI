package com.project.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 9)
@Component
public class GenderValidationRegister implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        CommitUserValidations.genderValueValidation(args.userDTO().getGender());

    }

}
