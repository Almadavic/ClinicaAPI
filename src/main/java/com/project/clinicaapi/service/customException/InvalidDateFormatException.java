package com.project.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidDateFormatException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDateFormatException() {
        super("Formato de data inválido, Formato válido: dd/MM/yyyy");
    }

    public InvalidDateFormatException(String dateField) {
        super("O campo "+ dateField+" está com o formato inválido, Formato de data válido: yyyy-mm-dd");
    }

}
