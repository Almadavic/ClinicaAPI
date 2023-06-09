package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.enumerated.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretaryMapper {

    private final PasswordEncoder encoder;

    public Secretary toSecretaryEntity(SecretaryRegisterDTO secretaryDTO) {

        return Secretary.secretaryBuilder()
                .login(secretaryDTO.getLogin())
                .email(secretaryDTO.getEmail())
                .name(secretaryDTO.getName())
                .cellphone(secretaryDTO.getCellphone())
                .gender(Gender.valueOf(secretaryDTO.getGender().toUpperCase()))
                .registration(secretaryDTO.getRegistration())
                .password(encoder.encode(getPassword(secretaryDTO.getPassword())))
                .country(secretaryDTO.getAddress().getCountry())
                .state(secretaryDTO.getAddress().getState())
                .city(secretaryDTO.getAddress().getCity())
                .build();
    }

    public SecretaryResponseDTO toSecretaryDTO(Secretary secretary) {
        return new SecretaryResponseDTO(secretary);
    }

    public Page<SecretaryResponseDTO> toSecretaryDTOPage(Page<Secretary> secretaries) {
        return secretaries.map(SecretaryResponseDTO::new);
    }

    private String getPassword(String passwordRequest) {

        if (passwordRequest != null) {
            return passwordRequest;
        }

        return "123456";
    }

}
