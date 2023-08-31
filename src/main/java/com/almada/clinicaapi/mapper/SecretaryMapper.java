package com.almada.clinicaapi.mapper;

import com.almada.clinicaapi.controller.SecretaryController;
import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.enumerated.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
        SecretaryResponseDTO secretaryDTO = new SecretaryResponseDTO(secretary);
        addHateoas(secretaryDTO);
        return secretaryDTO;
    }

    public Page<SecretaryResponseDTO> toSecretaryDTOPage(Page<Secretary> secretaries) {
        Page<SecretaryResponseDTO> secretariesDTO = secretaries.map(SecretaryResponseDTO::new);
        secretariesDTO.forEach(this::addHateoas);
        return secretariesDTO;
    }

    private void addHateoas(SecretaryResponseDTO secretaryDTO) {
        secretaryDTO.add(linkTo(methodOn(SecretaryController.class).findById(secretaryDTO.getId())).withSelfRel());
        secretaryDTO.add(linkTo(methodOn(SecretaryController.class).findPage(null)).withRel(IanaLinkRelations.COLLECTION));
        secretaryDTO.add(linkTo(methodOn(SecretaryController.class).update(secretaryDTO.getId(), null, null))
                .withRel("update"));
    }

    private String getPassword(String passwordRequest) {

        if (passwordRequest != null) {
            return passwordRequest;
        }

        return "123456";
    }

}
