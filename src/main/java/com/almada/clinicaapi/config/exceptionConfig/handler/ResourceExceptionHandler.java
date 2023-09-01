package com.almada.clinicaapi.config.exceptionConfig.handler;

import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler.validation.*;
import com.almada.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError.StandardErrorFieldsNotValid;
import com.almada.clinicaapi.config.exceptionConfig.standardError.beanValidationStandardError.ValidationErrorCollection;
import com.almada.clinicaapi.config.exceptionConfig.standardError.commomStandardError.StandardError;
import com.almada.clinicaapi.service.customException.*;
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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorCollection> handleValidations(MethodArgumentNotValidException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        String error = "There is one or more invalid parameters";

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
                new DateTimeExceptionInstance(
                        new JsonParseExceptionInstance(
                                new InvalidFormatExceptionInstance(
                                        new UnrecognizedPropertyExceptionInstance(
                                                new MismatchedInputExceptionInstance(
                                                        new GenericExceptionInstance()))))));

        return verification.verification(new FindExceptionInstanceArgs(rootCause, request, exception));
    }

    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<StandardError> mappingNotFound(HttpServletRequest request) {
        return handlingException(new ResourceNotFoundException("The resource is not mapped"), request, "Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Information being passed wrong to the server", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<StandardError> httpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException exception, HttpServletRequest request) {
        return handlingException(exception, request, "This media type is not acceptable", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> missingServletRequestParameterException(MissingServletRequestParameterException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = exception.getMessage().split("'")[1] + " cannot be null";

        StandardError err = new StandardError(status.value(), "Missing parameter value", message, request.getRequestURI());
        log(exception);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> methodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Method not allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<StandardError> propertyReference(PropertyReferenceException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Property Reference error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<StandardError> noFieldFilled(SendEmailException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Send email Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoFieldFilledException.class)
    public ResponseEntity<StandardError> noFieldFilled(NoFieldFilledException exception, HttpServletRequest request) {
        return handlingException(exception, request, "No value filled", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DentistNotAvailableException.class)
    public ResponseEntity<StandardError> dentistAvailableDay(DentistNotAvailableException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Unavailable day", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotEnable.class)
    public ResponseEntity<StandardError> dentistAvailableDay(UserNotEnable exception, HttpServletRequest request) {
        return handlingException(exception, request, "Unavailable user", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentDurationException.class)
    public ResponseEntity<StandardError> appointmentDuration(AppointmentDurationException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Appointment duration error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisableOwnAccountException.class)
    public ResponseEntity<StandardError> disableOwnAccount(DisableOwnAccountException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Disable account error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClinicOpeningHoursException.class)
    public ResponseEntity<StandardError> clinicOpeningHours(ClinicOpeningHoursException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Commit appointment error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidInstanceException.class)
    public ResponseEntity<StandardError> invalidInstance(InvalidInstanceException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid cast", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WorkDayNumberSizeException.class)
    public ResponseEntity<StandardError> workDayNumberSize(WorkDayNumberSizeException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Value not accepted", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<StandardError> noPermission(NoPermissionException exception, HttpServletRequest request) {
        return handlingException(exception, request, "No permission", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = JWTException.class)
    public ResponseEntity<StandardError> jwt(JWTException exception, HttpServletRequest request) {
        return handlingException(exception, request, "JWT error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<StandardError> dataBase(DatabaseException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Database Error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidCellphoneNumberFormatException.class)
    public ResponseEntity<StandardError> invalidCellPhoneNumberFormat(InvalidCellphoneNumberFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid cellphone", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidPasswordFormatException.class)
    public ResponseEntity<StandardError> invalidPasswordFormat(InvalidPasswordFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid password", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidEmailFormatException.class)
    public ResponseEntity<StandardError> invalidEmailFormat(InvalidEmailFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "invalid e-mail", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidNameFormatException.class)
    public ResponseEntity<StandardError> invalidNameFormat(InvalidNameFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid name", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidCpfFormatException.class)
    public ResponseEntity<StandardError> invalidCpfFormat(InvalidCpfFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid cpf", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidCroFormatException.class)
    public ResponseEntity<StandardError> invalidCroFormat(InvalidCroFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid cro", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> emailAlreadyRegistered(EmailAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Registering user error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CpfAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> cpfAlreadyRegistered(CpfAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Registering patient error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CroAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> croAlreadyRegistered(CroAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Registering dentist error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = LoginAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> loginAlreadyRegistered(LoginAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Registering user error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RegistrationAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> registrationAlreadyRegistered(RegistrationAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Registering secretary error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CellphoneAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> cellphoneAlreadyRegistered(CellphoneAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Registering user error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ParameterMissingException.class)
    public ResponseEntity<StandardError> parameterMissing(ParameterMissingException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Required parameters missing", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordDoesntMatchException.class)
    public ResponseEntity<StandardError> passwordsDontMatchException(PasswordDoesntMatchException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Password error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidEnumValueException.class)
    public ResponseEntity<StandardError> invalidEnumValue(InvalidEnumValueException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid value", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DateOrderException.class)
    public ResponseEntity<StandardError> dateOrder(DateOrderException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid date order", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDateFormatException.class)
    public ResponseEntity<StandardError> invalidDateFormat(InvalidDateFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid date format", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Resource not found", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> genericException(RuntimeException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
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
