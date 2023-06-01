package com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

public class UnrecognizedPropertyExceptionInstance extends FindExceptionInstance {

    public UnrecognizedPropertyExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        if (args.rootCause() instanceof UnrecognizedPropertyException) {

            String [] splitError = args.exception().getMessage().split(" ");

            return ResponseEntity.status(status).body(new StandardError(
                    status.value(),
                    "Campo não reconhecido: "+splitError[5].replace("\"", ""),
                    "Nome (chave) de campo inválido não são aceitos",
                    args.request().getRequestURI()));
        }

        return nextOne.verification(args);

    }

}
