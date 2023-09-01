package com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;

import com.fasterxml.jackson.core.JsonParseException;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

public class JsonParseExceptionInstance extends FindExceptionInstance {


    public JsonParseExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        Throwable rootCause = args.rootCause();

        if (rootCause instanceof JsonParseException) {

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "JSON error",
                    "Verify the JSON format",
                    args.request().getRequestURI()));
        }

        return nextOne.verification(args);

    }

}

