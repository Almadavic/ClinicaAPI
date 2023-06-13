package com.project.clinicaapi.util;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;

import java.util.Arrays;
import java.util.List;

public class VerifyAttributes {

    private VerifyAttributes() {

    }

    public static boolean commonAndAddressFieldsNull(List<Object> attributes, AddressUpdateDTO addressDTO) {
        return (commonAttributesNull(attributes) && addressDTO == null) || addressFieldsNull(attributes, addressDTO);
    }

    private static boolean addressFieldsNull(List<Object> attributes, AddressUpdateDTO addressDTO) {
        return (addressDTO != null && commonAttributesNull(Arrays.asList(addressDTO.getCountry(), addressDTO.getState(), addressDTO.getCity()))
                && commonAttributesNull(attributes));
    }

    public static boolean commonAttributesNull(List<Object> attributes) {

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
