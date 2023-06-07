package com.project.clinicaapi.util;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;

import java.util.Arrays;
import java.util.List;

public class VerifyAttributes {

    private VerifyAttributes() {

    }

    public static boolean addressFieldsNullToo(AddressUpdateDTO addressDTO, List<Object> attributes) {
        return addressDTO != null && allNAttributesNull(Arrays.asList(addressDTO.getCountry(), addressDTO.getState(), addressDTO.getCity()))
                && allNAttributesNull(attributes);
    }

    public static boolean allNAttributesNull(List<Object> attributes) {

        boolean allNull = true;

        for(Object attribute : attributes) {
            if (attribute != null) {
                allNull = false;
                break;
            }
        }

        return allNull;
    }

}
