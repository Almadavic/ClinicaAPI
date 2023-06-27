package com.project.clinicaapi.service.businessRule.commitUser;

import com.project.clinicaapi.service.customException.InvalidPasswordFormatException;

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
