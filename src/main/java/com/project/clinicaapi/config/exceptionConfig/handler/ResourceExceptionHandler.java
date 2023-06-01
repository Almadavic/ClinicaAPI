package com.project.clinicaapi.config.exceptionConfig.handler;

import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.project.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation.*;
import com.project.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError.StandardErrorFieldsNotValid;
import com.project.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError.ValidationErrorCollection;
import com.project.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorCollection> handleValidations(MethodArgumentNotValidException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        String error = "Há um ou mais parametros inválidos";

        ValidationErrorCollection validationErrs = new ValidationErrorCollection(status.value(), path, error);

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            String field = e.getField();
            validationErrs.addStandardErrorArgsNotValid(new StandardErrorFieldsNotValid(field, message));
        });
        log(exception);
        return ResponseEntity.status(status).body(validationErrs);

    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadable(HttpMessageNotReadableException exception, HttpServletRequest request) {

        log(exception);
        Throwable rootCause = ExceptionUtils.getRootCause(exception);

        FindExceptionInstance verification = new DateTimeParseExceptionInstance(
                new JsonParseExceptionInstance(
                        new InvalidFormatExceptionInstance(
                                new UnrecognizedPropertyExceptionInstance(
                                        new GenericExceptionInstance()))));

        return verification.verification(new FindExceptionInstanceArgs(rootCause, request, exception));
    }

    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<StandardError> mappingNotFound(HttpServletRequest request) {
        return handlingException(new ResourceNotFoundException("O recurso não está mapeado"), request, "Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Informação sendo passada errada para o servidor", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError>missingServletRequestParameterException(MissingServletRequestParameterException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = exception.getMessage().split("'")[1] + " não pode ser nulo";

        StandardError err = new StandardError(status.value(), "Valor de parametro faltando", message, request.getRequestURI());
        log(exception);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> methodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Método não permitido", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<StandardError> propertyReference(PropertyReferenceException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Property Reference error", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Recurso não encontrado", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> genericException(RuntimeException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
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
