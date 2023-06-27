package com.project.clinicaapi.service.businessRule.commitUser;

import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;
import com.project.clinicaapi.util.ListEnumValues;

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
