package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.InvalidNameFormatException;

public class NameFormat {

    private NameFormat() {

    }

    public static void verification(String nome) {

        String nameValidFormat = "^[a-zA-ZÀ-ú]+([ ][a-zA-ZÀ-ú]+)*$";

        if (!nome.matches(nameValidFormat)) {
            throw new InvalidNameFormatException(nome);
        }

    }

}
