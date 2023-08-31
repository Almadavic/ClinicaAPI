package com.almada.clinicaapi.config.exceptionConfig.handler;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


public class UnauthorizedHandler extends AuthorizationAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {

        if (!response.isCommitted()) {

            Object exception = request.getAttribute("exception");

            status = HttpStatus.UNAUTHORIZED.value();
            error = "Not authenticated";
            messageError = exception != null ? exception.toString().split(":")[1] : "In order to access that resource, you have to be logged in the system";

            responseClient(request, response, status, error, messageError);

        }

    }

}