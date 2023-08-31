package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.PasswordDoesntMatchException;

public class PasswordMatch {

    private PasswordMatch() {

    }

    public static void verification(String password, String passwordConfirmation) {

        if (password != null && (!password.equals(passwordConfirmation))) {
            throw new PasswordDoesntMatchException();
        }

    }

}
