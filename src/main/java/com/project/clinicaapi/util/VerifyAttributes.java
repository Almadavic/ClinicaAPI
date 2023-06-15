package com.project.clinicaapi.util;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;

import java.util.Arrays;
import java.util.List;

public class VerifyAttributes {

    private VerifyAttributes() {

    }

    public static boolean allAttributesNull(List<Object> attributes) {

        boolean allNull = true;

        for (Object attribute : attributes) {
            if (attribute != null) {
                allNull = false;
                break;
            }
        }

        return allNull;
    }

}
