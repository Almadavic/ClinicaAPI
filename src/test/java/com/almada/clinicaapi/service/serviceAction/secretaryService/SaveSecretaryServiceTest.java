package com.almada.clinicaapi.service.serviceAction.secretaryService;

import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.dto.response.SecretaryResponseDTO;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.enumerated.Gender;
import com.almada.clinicaapi.factory.SecretaryFactory;
import com.almada.clinicaapi.repository.SecretaryRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary.RegisterSecretaryVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.almada.clinicaapi.service.serviceAction.EnableAccountService;
import com.almada.clinicaapi.service.serviceAction.SecretaryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SaveSecretaryServiceTest {

    @Autowired
    private SecretaryService secretaryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecretaryRepository secretaryRepository; // LEMBRAR DE MOCKAR APENAS O FINDBYID, COMO ? CRIANDO UM OUTRO REPOSITORY MOCK

    @MockBean
    private EnableAccountService enableAccountService;

    @Autowired
    private SecretaryFactory secretaryFactory;

    @MockBean
    private List<RegisterUserVerification> registerUserVerifications;

    @MockBean
    private List<RegisterSecretaryVerification> registerSecretaryVerifications;

    @Autowired
    private PasswordEncoder encoder;

    private SecretaryRegisterDTO secretaryDTO;

    private Secretary secretary;

    @BeforeEach
    void setUp() {
        this.secretaryDTO = secretaryFactory.dtoRegister();
        this.secretary = secretaryFactory.entity();
    }

    @AfterEach
    void tearDown() {
        secretaryRepository.deleteAll();
    }

    @Test
    void noPasswordEntered() {

        // colocar o verify da lista

        secretaryDTO.setPassword(null);
        secretaryDTO.setPasswordConfirmation(null);

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.save(secretaryDTO, secretary);

        Secretary secretaryPasswordChanged = findSecretaryByLogin(secretaryResponseDTO.getLogin());

        Assertions.assertTrue(encoder.matches("123456", secretaryPasswordChanged.getPassword()));
        Assertions.assertEquals(Gender.MALE, Gender.valueOf(secretaryResponseDTO.getGender()));

    }

    @Test
    void passwordEntered() {

        // colocar o verify da lista

        SecretaryResponseDTO secretaryResponseDTO = secretaryService.save(secretaryDTO, secretary);

        Secretary secretaryPasswordChanged = findSecretaryByLogin(secretaryResponseDTO.getLogin());

        Assertions.assertTrue(encoder.matches(secretaryDTO.getPassword(), secretaryPasswordChanged.getPassword()));

    }

    private Secretary findSecretaryByLogin(String login) {
        return (Secretary) userRepository.findByLogin(login).get();
    }

}
