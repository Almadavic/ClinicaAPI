package com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;


import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

public class GenericExceptionInstance extends FindExceptionInstance {

    public GenericExceptionInstance() {
        super(null);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        StandardError err = new StandardError(status.value(), "Client error", args.exception().getMessage(), args.request().getRequestURI());

        return ResponseEntity.ok().body(err);
    }

}
