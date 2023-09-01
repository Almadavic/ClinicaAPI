package com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;


import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

import java.time.DateTimeException;


public class DateTimeExceptionInstance extends FindExceptionInstance {

    public DateTimeExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

         Throwable throwable = args.rootCause();

        if (throwable instanceof DateTimeException) {

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "Invalid value to date",
                    throwable.getMessage(),
                    args.request().getRequestURI()));

        }

        return nextOne.verification(args);

    }

}

