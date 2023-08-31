package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.InvalidPasswordFormatException;

public class PasswordFormat {

    private PasswordFormat() {

    }

    public static void verification(String password) {

        if (password != null) {

            String passwordValidFormat = "^(?=.*[A-Z])(?=.*\\d).+$";

            if (!password.matches(passwordValidFormat)) {
                throw new InvalidPasswordFormatException(password);
            }

        }

    }

}
