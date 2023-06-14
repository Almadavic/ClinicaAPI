package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.DentistSpecifications;
import com.project.clinicaapi.util.ListEnumValues;
import com.project.clinicaapi.util.LogRegistration;
import com.project.clinicaapi.util.mapper.DentistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DentistService {

    private final DentistRepository dentistRepository;

    private final DentistMapper mapper;

    private final LogRegistration logRegistration;

    public DentistResponseDTO save(DentistRegisterDTO registerData, User userLogged) {

        Dentist dentist = mapper.toDentistEntity(registerData);

        DentistResponseDTO dentistDTO = saveAndConvert(dentist);

        logRegistration.saveLog(userLogged.getUsername(), "registered the dentist: " + dentist.getUsername());

        return dentistDTO;
    }

    public Page<DentistResponseDTO> findPage(Pageable pageable, String name, String specialty) {

        Specification<Dentist> spec = DentistSpecifications.filter(name, specialtyValueValidation(specialty));

        return mapper.toDentistDTOPage(dentistRepository.findAll(spec, pageable));
    }

    public DentistResponseDTO findById(String dentistId) {
        return mapper.toDentistDTO(returnDentistDataBase(dentistId));
    }

    public DentistResponseDTO findByCro(String cro) {
        return mapper.toDentistDTO(
                dentistRepository.findByCro(cro)
                        .orElseThrow(() -> new ResourceNotFoundException("The dentist cro: " + cro + " wasn't found on database")));
    }

    public DentistResponseDTO update(String dentistId, DentistUpdateDTO updateData, User userLogged) {

        Dentist dentist = returnDentistDataBase(dentistId);

        DentistResponseDTO dentistDTO = saveAndConvert(dentist);

        logRegistration.saveLog(userLogged.getUsername(), "updated the dentist: " + dentist.getUsername());

        return dentistDTO;
    }

    private DentistResponseDTO saveAndConvert(Dentist dentist) {
        return mapper.toDentistDTO(dentistRepository.save(dentist));
    }

    private Dentist returnDentistDataBase(String dentistId) {
        return dentistRepository.findById(dentistId)
                .orElseThrow(() -> new ResourceNotFoundException("The dentist id: " + dentistId + " wasn't found on database"));
    }

    private static Specialty specialtyValueValidation(String specialty) {
        try {
            if(specialty != null) {
                return Specialty.valueOf(specialty.toUpperCase());
            }
            return null;
        } catch (IllegalArgumentException exception) {
            throw new InvalidEnumValueException(specialty, "Specialty", ListEnumValues.returnEnumValues(Arrays.asList(Specialty.values())));
        }

    }

}
