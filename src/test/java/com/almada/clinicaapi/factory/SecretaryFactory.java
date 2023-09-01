package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.enumerated.Gender;
import org.springframework.stereotype.Component;

@Component
public class SecretaryFactory {

    public Secretary entity() {
        return Secretary.secretaryBuilder()
                .login("secretary")
                .name("nome3")
                .password("123456")
                .country("Brasil")
                .cellphone("1931131144")
                .state("MG")
                .city("Belo Horizonte")
                .email("secratary@hotmail.com")
                .gender(Gender.MALE)
                .registration("1156139862302")
                .build();
    }

    public SecretaryResponseDTO dtoResponse() {
        return new SecretaryResponseDTO(entity());
    }

    public SecretaryRegisterDTO dtoRegister() {
        return SecretaryRegisterDTO.builder()
                .login("secretary")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .name("nome3")
                .password("123456")
                .cellphone("1931131144")
                .email("secratary@hotmail.com")
                .gender("MALE")
                .registration("1156139862302")
                .build();
    }

    public SecretaryUpdateDTO dtoUpdate() {
        return SecretaryUpdateDTO.builder()
                .login("secretary")
                .address(new AddressUpdateDTO("country", "state", "city"))
                .name("nome3")
                .password("123456")
                .cellphone("1931131144")
                .email("secratary@hotmail.com")
                .gender("MALE")
                .registration("1156139862302")
                .build();
    }

}
