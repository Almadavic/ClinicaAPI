package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class SendEmailException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public SendEmailException() {
        super("It wasn't possible to send the e-mail");
    }

}
