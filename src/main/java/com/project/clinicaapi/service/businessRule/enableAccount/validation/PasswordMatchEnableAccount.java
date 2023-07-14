package com.project.clinicaapi.service.businessRule.enableAccount.validation;

import com.project.clinicaapi.dto.request.EnableAccountDTO;
import com.project.clinicaapi.service.businessRule.commitUser.PasswordMatch;
import com.project.clinicaapi.service.businessRule.enableAccount.EnableAccountArgs;
import com.project.clinicaapi.service.businessRule.enableAccount.EnableAccountVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class PasswordMatchEnableAccount implements EnableAccountVerification {

    @Override
    public void verification(EnableAccountArgs args) {

       EnableAccountDTO enableAccountDTO =  args.enableAccountDTO();

        PasswordMatch.verification(enableAccountDTO.getPassword(), enableAccountDTO.getPasswordConfirmation());

    }

}
