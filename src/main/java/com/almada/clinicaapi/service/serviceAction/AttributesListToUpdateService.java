package com.almada.clinicaapi.service.serviceAction;

import com.almada.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AttributesListToUpdateService {

    public List<Object> getAttributesGenericsUser(UserUpdateDTO userData) {

        List<Object> attributes = new ArrayList<>(Arrays.asList(userData.getLogin(), userData.getName(), userData.getEmail(), userData.getPassword(),
                userData.getGender(), userData.getCellphone(), userData.getPasswordConfirmation()));

        addAddressAttributesToList(attributes, userData.getAddress());

        return attributes;
    }

    public boolean allAttributesNull(List<Object> attributes) {

        boolean allNull = true;

        for (Object attribute : attributes) {
            if (attribute != null) {
                allNull = false;
                break;
            }
        }

        return allNull;
    }

    private void addAddressAttributesToList(List<Object> attributes, AddressUpdateDTO addressDTO) {

        if (addressDTO != null) {
            attributes.addAll(Arrays.asList(addressDTO.getCountry(), addressDTO.getState(), addressDTO.getCity()));
        }
    }

}
