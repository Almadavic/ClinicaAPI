package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.enumerated.Gender;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecretaryMapper {

    public Secretary toSecretaryEntity(SecretaryRegisterDTO secretaryDTO, PasswordEncoder encoder) {

        String password = "123456";

        if(secretaryDTO.getPassword() != null) {
            password = secretaryDTO.getPassword();
        }

        return Secretary.secretaryBuilder()
                .login(secretaryDTO.getLogin())
                .email(secretaryDTO.getEmail())
                .name(secretaryDTO.getName())
                .cellphone(secretaryDTO.getCellphone())
                .gender(Gender.valueOf(secretaryDTO.getGender()))
                .registration(secretaryDTO.getRegistration())
                .password(encoder.encode(password))
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

}
