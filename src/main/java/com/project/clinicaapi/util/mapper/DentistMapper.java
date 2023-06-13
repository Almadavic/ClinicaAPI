package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.enumerated.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class DentistMapper {

    public Dentist toDentistEntity(DentistRegisterDTO dentistDTO) {
        return Dentist.dentistBuilder()
                .login(dentistDTO.getLogin())
                .email(dentistDTO.getEmail())
                .name(dentistDTO.getName())
                .cellphone(dentistDTO.getCellphone())
                .gender(Gender.valueOf(dentistDTO.getGender().toUpperCase()))
                .cro(dentistDTO.getCro())
                .specialty(Specialty.valueOf(dentistDTO.getSpeciality().toUpperCase()))
                .country(dentistDTO.getAddress().getCountry())
                .state(dentistDTO.getAddress().getState())
                .city(dentistDTO.getAddress().getCity())
                .build();
    }

    public DentistResponseDTO toDentistDTO(Dentist dentist) {
        return new DentistResponseDTO(dentist);
    }

    public Page<DentistResponseDTO> toDentistDTOPage(Page<Dentist> dentists) {
        return dentists.map(DentistResponseDTO::new);
    }

}
