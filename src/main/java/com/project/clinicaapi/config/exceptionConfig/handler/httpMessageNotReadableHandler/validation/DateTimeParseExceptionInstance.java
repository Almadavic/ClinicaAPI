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

            String message = String.format("Received value '%s' for date, which is of an invalid type. ", ex.getParsedString());

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "Invalid Date Format",
                    message + " Proper date format: yyyy-mm-dd",
                    args.request().getRequestURI()));

        }

        return nextOne.verification(args);

    }

}

