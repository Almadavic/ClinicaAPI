package com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

public class MismatchedInputExceptionInstance extends FindExceptionInstance {

    public MismatchedInputExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        if (args.rootCause() instanceof MismatchedInputException ex) {

            String path = joinPath(ex.getPath());

            String message = String.format("The property '%s' received a invalid value " +
                            "Correct and enter a value compatible with type %s.", path,
                    ex.getTargetType().getSimpleName());

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "Invalid type for list",
                    message,
                    args.request().getRequestURI()));

        }

        return nextOne.verification(args);
    }

}
