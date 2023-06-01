package com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler;

import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public abstract class FindExceptionInstance {

    protected FindExceptionInstance nextOne;

    protected static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public abstract ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args);
}
