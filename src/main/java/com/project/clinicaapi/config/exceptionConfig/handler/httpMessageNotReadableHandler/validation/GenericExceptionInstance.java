package com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;


import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

public class GenericExceptionInstance extends FindExceptionInstance {

    public GenericExceptionInstance() {
        super(null);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        StandardError err = new StandardError(status.value(), "Erro no client", args.exception().getMessage(), args.request().getRequestURI());

        return ResponseEntity.ok().body(err);
    }

}
