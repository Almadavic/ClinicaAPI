package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
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
                .password("123456@Ab")
                .gender(Gender.MALE)
                .build();
    }

    public UserUpdateDTO dtoUpdate() {
        return UserUpdateDTO.builder()
                .login("login")
                .name("name")
                .email("email@hotmail.com")
                .address(new AddressUpdateDTO("country", "state", "city"))
                .cellphone("(31)98589-8955")
                .password("123456@Ab")
                .gender("MALE")
                .passwordConfirmation("123456@Ab")
                .build();
    }

}
