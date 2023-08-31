package com.almada.clinicaapi.service.serviceAction;


import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.mapper.SecretaryMapper;
import com.almada.clinicaapi.repository.SecretaryRepository;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;

    private final SecretaryMapper mapper;

    private final LogRegistration logRegistration;

    private final List<RegisterUserVerification> registerUserVerifications;

    private final List<RegisterSecretaryVerification> registerSecretaryVerifications;

    private final List<UpdateUserVerification> updateUserVerifications;

    private final List<UpdateSecretaryVerification> updateSecretaryVerifications;

    @CacheEvict(value = {"secretariesPage", "usersPage"}, allEntries = true)
    public SecretaryResponseDTO save(SecretaryRegisterDTO registerData, User userLogged) {

        saveSecretaryVerifications(registerData);

        Secretary secretary = mapper.toSecretaryEntity(registerData);

        SecretaryResponseDTO secretaryDTO = saveAndConvertToDTO(secretary);

        logRegistration.saveLog(userLogged.getUsername(), "registered the secretary: " + secretary.getUsername());

        return secretaryDTO;
    }

    @Cacheable(value = "secretariesPage")
    public Page<SecretaryResponseDTO> findPage(Pageable pageable) {
        return mapper.toSecretaryDTOPage(secretaryRepository.findAll(pageable));
    }

    public SecretaryResponseDTO findById(String secretaryId) {
        return mapper.toSecretaryDTO(returnSecretaryDataBase(secretaryId));
    }

    public SecretaryResponseDTO findByRegistration(String registration) {
        return mapper.toSecretaryDTO(
                secretaryRepository.findByRegistration(registration)
                        .orElseThrow(() -> new ResourceNotFoundException("The secretary registration: " + registration + " wasn't found on database")));
    }

    @CacheEvict(value = {"secretariesPage", "usersPage"}, allEntries = true)
    public SecretaryResponseDTO update(String secretaryId, SecretaryUpdateDTO updateData, User userLogged) {

        Secretary secretary = returnSecretaryDataBase(secretaryId);

        updateSecretaryVerifications(updateData, secretary);

        SecretaryResponseDTO secretaryDTO = saveAndConvertToDTO(secretary);

        logRegistration.saveLog(userLogged.getUsername(), "updated the secretary: " + secretary.getUsername());

        return secretaryDTO;
    }

    private void saveSecretaryVerifications(SecretaryRegisterDTO registerData) {
        registerUserVerifications.forEach(v -> v.verification(new RegisterUserArgs(registerData)));
        registerSecretaryVerifications.forEach(v -> v.verification(new RegisterSecretaryArgs(registerData, secretaryRepository)));
    }

    private void updateSecretaryVerifications(SecretaryUpdateDTO updateData, Secretary secretary) {
        updateSecretaryVerifications.forEach(v -> v.verification(new UpdateSecretaryArgs(updateData, secretary, secretaryRepository)));
        updateUserVerifications.forEach(v -> v.verification(new UpdateUserArgs(updateData, secretary)));
    }

    private Secretary returnSecretaryDataBase(String secretaryId) {
        return secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new ResourceNotFoundException("The secretary id: " + secretaryId + " wasn't found on database"));
    }

    private SecretaryResponseDTO saveAndConvertToDTO(Secretary secretary) {
        return mapper.toSecretaryDTO(secretaryRepository.save(secretary));
    }

}
