package com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

public class UnrecognizedPropertyExceptionInstance extends FindExceptionInstance {

    public UnrecognizedPropertyExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        if (args.rootCause() instanceof UnrecognizedPropertyException) {

            String[] splitError = args.exception().getMessage().split(" ");

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "Field not recognized: " + splitError[5].replace("\"", ""),
                    "Name (key) of invalid fields are not accepted",
                    args.request().getRequestURI()));
        }

        return nextOne.verification(args);

    }

}
