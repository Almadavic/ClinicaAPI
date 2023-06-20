package com.project.clinicaapi.service.attributesListToUpdateService;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.UserUpdateDTO;
import com.project.clinicaapi.service.serviceAction.AttributesListToUpdateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@ActiveProfiles(value = "test")
@SpringBootTest
class AllAttributesNull {

    @Autowired
    private AttributesListToUpdateService service;

    @Test
    void getAttributesGenericsUserNoAddress() {

       List<Object> attributes =  service.getAttributesGenericsUser(UserUpdateDTO.builder().build());

        Assertions.assertEquals(7, attributes.size());

    }

    @Test
    void getAttributesGenericsUserwithAddress() {

        List<Object> attributes =  service.getAttributesGenericsUser(
                UserUpdateDTO.builder()
                        .address(new AddressUpdateDTO())
                        .build());

        Assertions.assertEquals(10, attributes.size());

    }

    @Test
    void notAllAttributesNull() {

        List<Object> attributes =  service.getAttributesGenericsUser(
                UserUpdateDTO.builder()
                        .login("login")
                        .name("name")
                        .build());

       Assertions.assertFalse(service.allAttributesNull(attributes));

    }

    @Test
    void allAttributesNull() {

        List<Object> attributes =  service.getAttributesGenericsUser(
                UserUpdateDTO.builder().build());

        Assertions.assertTrue(service.allAttributesNull(attributes));

    }

}
