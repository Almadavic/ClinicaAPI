package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class AnotherMeetingRunningException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AnotherMeetingRunningException(String userType) {
        super("The " + userType + " has an another appointment that time");
    }

}
