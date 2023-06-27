package com.project.clinicaapi.service.businessRule.commitUser;

import com.project.clinicaapi.service.customException.InvalidEmailFormatException;

public class EmailFormat {

    private EmailFormat() {

    }

    public static void verification(String email) {

        String emailValidFormat = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!email.matches(emailValidFormat)) {
            throw new InvalidEmailFormatException(email);
        }

    }

}
