package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.ParameterMissingException;

public class PasswordNull {

    private PasswordNull() {

    }

    public static void verification(String password, String passwordConfirmation) {

        if ((password != null && passwordConfirmation == null)
                || (password == null && passwordConfirmation != null)) {
            throw new ParameterMissingException("In order to register your account and set a password, you have to enter the fields 'password' and 'passwordconfirmation'.");
        }


    }

}
