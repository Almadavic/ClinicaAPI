package com.project.clinicaapi.service.businessRule.disableAccount.validation;

import com.project.clinicaapi.service.businessRule.disableAccount.DisableAccountArgs;
import com.project.clinicaapi.service.businessRule.disableAccount.DisableAccountVerification;
import com.project.clinicaapi.service.customException.DisableOwnAccountException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class DisableOwnAccount implements DisableAccountVerification {

    @Override
    public void verification(DisableAccountArgs args) {

        String idUserLogged = args.userLogged().getId();

        String idToBeDisable = args.toBeDisable().getId();

        if (idUserLogged.equals(idToBeDisable)) {
            throw new DisableOwnAccountException();
        }

    }

}
