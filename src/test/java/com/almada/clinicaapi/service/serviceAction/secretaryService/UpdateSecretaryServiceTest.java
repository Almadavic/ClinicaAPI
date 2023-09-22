package com.almada.clinicaapi.service.serviceAction.secretaryService;

import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.repository.SecretaryRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.almada.clinicaapi.service.serviceAction.SecretaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UpdateSecretaryServiceTest {

    @Autowired
    private SecretaryService secretaryService;

    @MockBean
    private List<UpdateUserVerification> updateUserVerifications;

    @MockBean
    private List<UpdateSecretaryVerification> updateSecretaryVerifications;

    @MockBean
    private SecretaryRepository secretaryRepository;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @Test
    void fieldsValue() {

        SecretaryUpdateDTO secretaryDTO = secretaryFactory.dtoUpdate();

        Secretary secretary = secretaryFactory.entity();

        when(secretaryRepository.findById(secretary.getId())).thenReturn(Optional.of(secretary));
        when(secretaryRepository.save(secretary)).thenReturn(secretary);
        // colocar o verify da lista

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.update(secretary.getId(), secretaryDTO,
                secretary);

        Assertions.assertEquals(secretaryDTO.getRegistration(), secretaryResponseDTO.getRegistration());

    }

}
