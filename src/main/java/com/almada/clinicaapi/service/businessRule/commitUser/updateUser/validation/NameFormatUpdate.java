package com.almada.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.NameFormat;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 6)
@Component
public class NameFormatUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        String name = args.userDTO().getName();

        if (name != null) {
            NameFormat.verification(name);
        }

    }

}
