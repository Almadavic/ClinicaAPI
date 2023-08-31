package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class AppointmentDurationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public AppointmentDurationException() {
        super("The appointment has to last between 30 and 60 minutes");
    }

}
