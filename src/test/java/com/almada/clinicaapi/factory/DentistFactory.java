package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.almada.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.dto.response.DentistResponseDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.enumerated.Specialty;
import org.springframework.stereotype.Component;

@Component
public class DentistFactory {

    public Dentist entity() {
        return  Dentist.dentistBuilder()
                .login("dentist1")
                .name("dentist1")
                .password("123456")
                .country("Brasil")
                .cellphone("(31)98858-8362")
                .state("MG")
                .city("Belo Horizonte")
                .email("dentist1@hotmail.com")
                .cro("137180")
                .specialty(Specialty.ORTHODONTICS)
                .gender(Gender.MALE)
                .build();
    }

    public DentistResponseDTO dtoResponse() {
        return new DentistResponseDTO(entity());
    }

    public DentistRegisterDTO dtoRegister() {

        return DentistRegisterDTO.builder()
                .login("dentist1")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .name("dentist1")
                .cellphone("(31)98858-8362")
                .email("dentist1@hotmail.com")
                .cro("137180")
                .speciality("othodontics")
                .gender("MALE")
                .build();
    }

    public DentistUpdateDTO dtoUpdate() {
       return DentistUpdateDTO.builder()
               .login("dentist1")
               .address(new AddressUpdateDTO("country", "state", "city"))
               .name("dentist1")
               .cellphone("(31)98858-8362")
               .email("dentist1@hotmail.com")
               .cro("137180")
               .speciality("othodontics")
               .gender("MALE")
               .build();
    }

}
