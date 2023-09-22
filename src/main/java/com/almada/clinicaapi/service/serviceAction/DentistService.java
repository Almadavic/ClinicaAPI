package com.almada.clinicaapi.service.serviceAction;


import com.almada.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.dto.response.DentistResponseDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.mapper.DentistMapper;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.repository.specification.DentistSpecifications;
import com.almada.clinicaapi.service.businessRule.commitDentist.SpecialtyValue;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DentistService {

    private final EnableAccountService enableAccountService;

    private final DentistRepository dentistRepository;

    private final DentistMapper mapper;

    private final LogRegistration logRegistration;

    private final WorkDayService workDayService;

    private final List<RegisterUserVerification> registerUserVerifications;

    private final List<RegisterDentistVerification> registerDentistVerifications;

    private final List<UpdateUserVerification> updateUserVerifications;

    private final List<UpdateDentistVerification> updateDentistVerifications;

    public DentistResponseDTO save(DentistRegisterDTO registerData, User userLogged) {

        saveDentistVerifications(registerData);

        Dentist dentist = mapper.toDentistEntity(registerData);

        setWorkDaysList(dentist, registerData);

        DentistResponseDTO dentistDTO = saveAndConvert(dentist);

        enableAccountService.sendCodeToEmail(dentist);

        logRegistration.saveLog(userLogged.getUsername(), "registered the dentist: " + dentist.getUsername());

        return dentistDTO;
    }


    public Page<DentistResponseDTO> findPage(Pageable pageable, String name, Boolean enabled, String specialty) {

        Specification<Dentist> spec = DentistSpecifications.filter(name, enabled, SpecialtyValue.verification(specialty));

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

        updateDentistVerifications(updateData, dentist);

        DentistResponseDTO dentistDTO = saveAndConvert(dentist);

        logRegistration.saveLog(userLogged.getUsername(), "updated the dentist: " + dentist.getUsername());

        return dentistDTO;
    }

    private void saveDentistVerifications(DentistRegisterDTO registerData) {
        registerUserVerifications.forEach(v -> v.verification(new RegisterUserArgs(registerData)));
        registerDentistVerifications.forEach(v -> v.verification(new RegisterDentistArgs(registerData, dentistRepository)));
    }

    private void updateDentistVerifications(DentistUpdateDTO updateData, Dentist dentist) {
        updateDentistVerifications.forEach(v -> v.verification(new UpdateDentistArgs(updateData, dentist, dentistRepository, workDayService)));
        updateUserVerifications.forEach(v -> v.verification(new UpdateUserArgs(updateData, dentist)));
    }

    private DentistResponseDTO saveAndConvert(Dentist dentist) {
        return mapper.toDentistDTO(dentistRepository.save(dentist));
    }

    private Dentist returnDentistDataBase(String dentistId) {
        return dentistRepository.findById(dentistId)
                .orElseThrow(() -> new ResourceNotFoundException("The dentist id: " + dentistId + " wasn't found on database"));
    }

    private void setWorkDaysList(Dentist dentist, DentistRegisterDTO dentistDTO) {

        Set<Integer> workDays = dentistDTO.getWorkDays();
        if (workDays != null) {
            workDays.forEach(workday ->
                    dentist.addWorkDay(workDayService.returnWorkDayDataBase(workday)));
        }

    }

}
