package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.almada.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.enumerated.Gender;
import org.springframework.stereotype.Component;

@Component
public class PatientFactory {

    public Patient entity() {
        return Patient.patientBuilder()
                .login("patient")
                .name("nome")
                .password("123456")
                .country("Brasil")
                .cellphone("(31)98589-8955")
                .state("MG")
                .city("Belo Horizonte")
                .email("sergio@hotmail.com")
                .gender(Gender.MALE)
                .cpf("115.613.986-02")
                .build();
    }

    public PatientResponseDTO dtoResponse() {
        return new PatientResponseDTO(entity());
    }

    public PatientRegisterDTO dtoRegister() {
        return PatientRegisterDTO.builder()
                .login("patient")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .name("nome")
                .cellphone("(31)98589-8955")
                .email("sergio@hotmail.com")
                .gender("MALE")
                .cpf("115.613.986-02")
                .build();
    }

    public PatientUpdateDTO dtoUpdate() {
        return PatientUpdateDTO.builder()
                .login("patient")
                .address(new AddressUpdateDTO("country", "state", "city"))
                .name("nome")
                .cellphone("(31)98589-8955")
                .email("sergio@hotmail.com")
                .gender("MALE")
                .cpf("115.613.986-02")
                .build();
    }

}
