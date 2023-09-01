package com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

public class InvalidFormatExceptionInstance extends FindExceptionInstance {

    public InvalidFormatExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        Throwable rootCause = args.rootCause();

        if (rootCause instanceof InvalidFormatException ex) {

            String path = joinPath(ex.getPath());

            String message = String.format("The property '%s' received the value '%s', which is of an invalid type. " +
                            "Correct and enter a value compatible with type %s.", path, ex.getValue(),
                    ex.getTargetType().getSimpleName());

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "Invalid value format",
                    message,
                    args.request().getRequestURI()));
        }

        return nextOne.verification(args);

    }
    
}
