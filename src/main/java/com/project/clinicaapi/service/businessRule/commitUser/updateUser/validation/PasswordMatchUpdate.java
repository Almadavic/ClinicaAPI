package com.project.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.project.clinicaapi.dto.request.update.UserUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitUser.PasswordMatch;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 8)
@Component
public class PasswordMatchUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        UserUpdateDTO userDTO = args.userDTO();

        PasswordMatch.verification(userDTO.getPassword(), userDTO.getPasswordConfirmation());

    }

}
