package com.almada.clinicaapi.service.businessRule.commitUser.registerUser.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.CellphoneFormat;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 4)
@Component
public class CellphoneFormatRegister implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        args.userDTO().setCellphone(CellphoneFormat.verification(args.userDTO().getCellphone()));

    }

}
