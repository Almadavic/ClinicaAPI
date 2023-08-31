package com.almada.clinicaapi.service.businessRule.enableAccount.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.PasswordFormat;
import com.almada.clinicaapi.service.businessRule.enableAccount.EnableAccountArgs;
import com.almada.clinicaapi.service.businessRule.enableAccount.EnableAccountVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class PasswordFormatEnableAccount implements EnableAccountVerification {

    @Override
    public void verification(EnableAccountArgs args) {

        PasswordFormat.verification(args.enableAccountDTO().getPassword());

    }

}
