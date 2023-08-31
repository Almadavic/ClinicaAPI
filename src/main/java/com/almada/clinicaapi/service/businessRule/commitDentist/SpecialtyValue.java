package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.enumerated.Specialty;
import com.almada.clinicaapi.service.customException.InvalidEnumValueException;
import com.almada.clinicaapi.util.ListEnumValues;

import java.util.Arrays;

public class SpecialtyValue {

    private SpecialtyValue() {

    }

    public static Specialty verification(String specialty) {
        try {
            if(specialty != null) {
                return Specialty.valueOf(specialty.toUpperCase());
            }
            return null;
        } catch (IllegalArgumentException exception) {
            throw new InvalidEnumValueException(specialty, "Specialty", ListEnumValues.returnEnumValues(Arrays.asList(Specialty.values())));
        }

    }

}
