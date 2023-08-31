package com.almada.clinicaapi.service.businessRule.commitUser.updateUser.validation;

import com.almada.clinicaapi.service.businessRule.commitUser.CellphoneFormat;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 4)
@Component
public class CellphoneFormatUpdate implements UpdateUserVerification {

    @Override
    public void verification(UpdateUserArgs args) {

        args.userDTO().setCellphone(CellphoneFormat.verification(args.userDTO().getCellphone()));

    }

}
