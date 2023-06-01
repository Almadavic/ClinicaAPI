package com.project.clinicaapi.service.customException;

import java.io.Serial;

public class ParameterMissingException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ParameterMissingException(String parameter) {
        super("O parâmetro " + parameter + " não pode ser nulo nesse tipo de consulta");
    }

}
