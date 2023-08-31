package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidCpfFormatException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCpfFormatException(String cpf) {
        super("The cpf: " + cpf + " contains an invalid format");
    }

}
