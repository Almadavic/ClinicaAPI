package com.almada.clinicaapi.mapper;

import com.almada.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.almada.clinicaapi.dto.response.DentistResponseDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.enumerated.Specialty;
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
        DentistResponseDTO dentistDTO = new DentistResponseDTO(dentist);
        addHateoas(dentistDTO);
        return dentistDTO;
    }

    public Page<DentistResponseDTO> toDentistDTOPage(Page<Dentist> dentists) {
        Page<DentistResponseDTO> dentistsDTO = dentists.map(DentistResponseDTO::new);
        dentistsDTO.forEach(this::addHateoas);
        return dentistsDTO;
    }

    private void addHateoas(DentistResponseDTO dentistDTO) {

    }

}
