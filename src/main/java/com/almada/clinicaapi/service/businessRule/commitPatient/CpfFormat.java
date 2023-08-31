package com.almada.clinicaapi.service.businessRule.commitPatient;

import com.almada.clinicaapi.service.customException.InvalidCpfFormatException;

public class CpfFormat {

    private CpfFormat() {

    }

    public static void verification(String cpf) {

        String cpfValidFormat = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

        if (!cpf.matches(cpfValidFormat)) {
            throw new InvalidCpfFormatException(cpf);
        }

    }

}
