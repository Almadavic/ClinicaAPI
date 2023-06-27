package com.project.clinicaapi.service.businessRule.commitPatient;

import com.project.clinicaapi.service.customException.InvalidCpfFormatException;

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
