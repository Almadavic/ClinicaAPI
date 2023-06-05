package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.LogRegistration;
import com.project.clinicaapi.util.mapper.SecretaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;

    private final PasswordEncoder encoder;

    private final SecretaryMapper mapper;

    private final LogRegistration logRegistration;

    public SecretaryResponseDTO save(SecretaryRegisterDTO registerData, User userLogged) {

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

    private Secretary returnSecretaryDataBase(String secretaryId) {
        return secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new ResourceNotFoundException("The secretary id: " + secretaryId + " wasn't found on database"));
    }

    private SecretaryResponseDTO saveAndConvert(Secretary secretary) {
        return mapper.toSecretaryDTO(secretaryRepository.save(secretary));
    }

}
