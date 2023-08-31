package com.almada.clinicaapi.service.businessRule.disableAccount.validation;

import com.almada.clinicaapi.enumerated.Role;
import com.almada.clinicaapi.service.businessRule.disableAccount.DisableAccountArgs;
import com.almada.clinicaapi.service.businessRule.disableAccount.DisableAccountVerification;
import com.almada.clinicaapi.service.customException.NoPermissionException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class PermissionToDisable implements DisableAccountVerification {

    @Override
    public void verification(DisableAccountArgs args) {

        String userLoggedRole = args.userLogged().getRole().toString();

        String toBeDisableRole = args.toBeDisable().getRole().toString();

        if(userLoggedRole.equals(Role.SECRETARY.toString()) && toBeDisableRole.equals(Role.ADMINISTRATOR.toString())) {
            throw new NoPermissionException("disable an administrator");
        }

    }

}
