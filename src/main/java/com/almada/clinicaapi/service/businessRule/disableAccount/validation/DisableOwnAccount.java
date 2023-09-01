package com.almada.clinicaapi.service.businessRule.disableAccount.validation;

import com.almada.clinicaapi.service.businessRule.disableAccount.DisableAccountArgs;
import com.almada.clinicaapi.service.businessRule.disableAccount.DisableAccountVerification;
import com.almada.clinicaapi.service.customException.DisableOwnAccountException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class DisableOwnAccount implements DisableAccountVerification {

    @Override
    public void verification(DisableAccountArgs args) {

        if (args.userLogged().equals(args.toBeDisable())) {
            throw new DisableOwnAccountException();
        }

    }

}
