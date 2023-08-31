package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.service.customException.InvalidEnumValueException;
import com.almada.clinicaapi.util.ListEnumValues;

import java.util.Arrays;

public class GenderValue {

    private GenderValue() {

    }

    public static void verification(String gender) {

        try {
            Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidEnumValueException(gender, "Gender", ListEnumValues.returnEnumValues(Arrays.asList(Gender.values())));
        }

    }

}
