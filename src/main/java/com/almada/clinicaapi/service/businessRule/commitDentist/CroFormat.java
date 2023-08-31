package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.service.customException.InvalidCroFormatException;

public class CroFormat {

    private CroFormat() {

    }

    public static void verification(String cro) {

        String croValidFormat = "^[0-9]+$";

        if (!cro.matches(croValidFormat)) {
            throw new InvalidCroFormatException(cro);
        }

    }

}
