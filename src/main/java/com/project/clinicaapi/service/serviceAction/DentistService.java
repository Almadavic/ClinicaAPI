package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.DentistSpecifications;
import com.project.clinicaapi.util.LogRegistration;
import com.project.clinicaapi.util.mapper.DentistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DentistService {

    private final DentistRepository dentistRepository;

    private final DentistMapper mapper;

    private final LogRegistration logRegistration;

    private final WorkDayService workDayService;

    private final List<RegisterUserVerification> registerUserVerifications;

    private final List<RegisterDentistVerification> registerDentistVerifications;

    public DentistResponseDTO save(DentistRegisterDTO registerData, User userLogged) {

        saveDentistVerifications(registerData);

        Dentist dentist = mapper.toDentistEntity(registerData);

        setDentistReferences(dentist, registerData);

        DentistResponseDTO dentistDTO = saveAndConvert(dentist);

        logRegistration.saveLog(userLogged.getUsername(), "registered the dentist: " + dentist.getUsername());

        return dentistDTO;
    }

    public Page<DentistResponseDTO> findPage(Pageable pageable, String name, String specialty) {

        Specification<Dentist> spec = DentistSpecifications.filter(name, CommitDentistValidations.specialtyValueValidation(specialty));

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

    private void saveDentistVerifications(DentistRegisterDTO registerData) {
        registerUserVerifications.forEach(v -> v.verification(new RegisterUserArgs(registerData)));
        registerDentistVerifications.forEach(v -> v.verification(new RegisterDentistArgs(registerData)));
    }

    private DentistResponseDTO saveAndConvert(Dentist dentist) {
        return mapper.toDentistDTO(dentistRepository.save(dentist));
    }

    private Dentist returnDentistDataBase(String dentistId) {
        return dentistRepository.findById(dentistId)
                .orElseThrow(() -> new ResourceNotFoundException("The dentist id: " + dentistId + " wasn't found on database"));
    }

    private void setDentistReferences(Dentist dentist, DentistRegisterDTO dentistDTO) {

        Set<Long> workDays = dentistDTO.getWorkDays();

        if (workDays != null) {
            workDays.forEach(workday ->
                    dentist.addWorkDay(workDayService.returnWorkDayDataBase(workday)));
        }

    }

}
