package com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;


import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

import java.time.format.DateTimeParseException;


public class DateTimeParseExceptionInstance extends FindExceptionInstance {

    public DateTimeParseExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {


        if (args.rootCause() instanceof DateTimeParseException ex) {

            String message = String.format("Foi recebido o valor de '%s' para data, que é de um tipo inválido. ", ex.getParsedString());

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "Formato de data inválido",
                    message + " Formato de data adequado: yyyy-mm-dd",
                    args.request().getRequestURI()));

        }

        return nextOne.verification(args);

    }

}

