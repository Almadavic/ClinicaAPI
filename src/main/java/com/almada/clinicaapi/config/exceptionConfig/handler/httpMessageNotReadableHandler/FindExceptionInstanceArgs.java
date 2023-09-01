package com.almada.clinicaapi.config.exceptionConfig.handler.httpMessageNotReadableHandler;

import jakarta.servlet.http.HttpServletRequest;

public record FindExceptionInstanceArgs(Throwable rootCause, HttpServletRequest request, Exception exception) {

}
