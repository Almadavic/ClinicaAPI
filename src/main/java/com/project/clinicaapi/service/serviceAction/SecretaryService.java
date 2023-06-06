package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.LogRegistration;
import com.project.clinicaapi.util.mapper.SecretaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;

    private final PasswordEncoder encoder;

    private final SecretaryMapper mapper;

    private final LogRegistration logRegistration;

    private final List<RegisterUserVerification> registerUserVerifications;

    private final List<RegisterSecretaryVerification> registerSecretaryVerifications;

    public SecretaryResponseDTO save(SecretaryRegisterDTO registerData, User userLogged) {

        saveSecretaryVerifications(registerData);

        Secretary secretary = mapper.toSecretaryEntity(registerData, encoder);

        SecretaryResponseDTO secretaryDTO = saveAndConvert(secretary);

        logRegistration.saveLog(userLogged.getUsername(), "registered the secretary: "+ secretary.getUsername());

        return secretaryDTO;
    }

    public Page<SecretaryResponseDTO> findPage(Pageable pageable) {
        return mapper.toSecretaryDTOPage(secretaryRepository.findAll(pageable));
    }

    public SecretaryResponseDTO findById(String secretaryId) {
        return mapper.toSecretaryDTO(returnSecretaryDataBase(secretaryId));
    }

    public SecretaryResponseDTO findByRegistration(String registration) {
        return mapper.toSecretaryDTO(
        secretaryRepository.findByRegistration(registration)
                .orElseThrow(() -> new ResourceNotFoundException("The secretary registration: " +registration + " wasn't found on database")));
    }

    private void saveSecretaryVerifications(SecretaryRegisterDTO registerData) {
        registerUserVerifications.forEach(v -> v.verification(new RegisterUserArgs(registerData)));
        registerSecretaryVerifications.forEach(v -> v.verification(new RegisterSecretaryArgs(registerData)));
    }

    private Secretary returnSecretaryDataBase(String secretaryId) {
        return secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new ResourceNotFoundException("The secretary id: " + secretaryId + " wasn't found on database"));
    }

    private SecretaryResponseDTO saveAndConvert(Secretary secretary) {
        return mapper.toSecretaryDTO(secretaryRepository.save(secretary));
    }

}
