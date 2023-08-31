package com.almada.clinicaapi.config.exceptionConfig.handler;

import com.almada.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError.StandardErrorFieldsNotValid;
import com.almada.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError.ValidationErrorCollection;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.service.customException.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ResourceExceptionHandler {

    private final MessageSource messageSource;

    private final Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);


    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<StandardError> dataBase(DatabaseException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Database Error", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<StandardError> handlingException(Exception exception, HttpServletRequest request, String error, HttpStatus status) {
        StandardError err = new StandardError(status.value(), error, exception.getMessage(), request.getRequestURI());
        log(exception);
        return ResponseEntity.status(status).body(err);
    }

    private void log(Throwable exception) {
        logger.error("error message {}. Details:", exception.getMessage(), exception);
    }


}
