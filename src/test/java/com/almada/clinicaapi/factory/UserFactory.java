package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.enumerated.Gender;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User entity() {
        return User.builder()
                .login("login")
                .name("name")
                .state("state")
                .city("city")
                .country("country")
                .email("email@hotmail.com")
                .cellphone("(31)98589-8955")
                .password("123456")
                .gender(Gender.MALE)
                .build();
    }

}
