package com.almada.clinicaapi.service.businessRule.changePasswordForgotten.validation;

import com.almada.clinicaapi.dto.request.ChangePasswordDTO;
import com.almada.clinicaapi.service.businessRule.changePasswordForgotten.ChangePasswordForgottenArgs;
import com.almada.clinicaapi.service.businessRule.changePasswordForgotten.ChangePasswordForgottenValidation;
import com.almada.clinicaapi.service.businessRule.commitUser.PasswordMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class PasswordMatchChangePasswordForgotten implements ChangePasswordForgottenValidation {

    @Override
    public void verification(ChangePasswordForgottenArgs args) {

        ChangePasswordDTO changePasswordDTO = args.changePasswordDTO();

        PasswordMatch.verification(changePasswordDTO.getPassword(), changePasswordDTO.getPasswordConfirmation());

    }

}
