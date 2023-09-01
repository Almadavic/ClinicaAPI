package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.factory.UserFactory;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.CellphoneAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@SpringBootTest
class CellphoneAvailableTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cellphoneUnavailable() {

        when(userRepository.findByCellphone("(31)98589-8955")).thenReturn(Optional.of(userFactory.entity()));

        Assertions.assertThrows(CellphoneAlreadyRegisteredException.class,
                () -> CellphoneAvailable.verification(userRepository, "(31)98589-8955"));

        Mockito.verify(userRepository).findByCellphone("(31)98589-8955");

    }

    @Test
    void cellphoneAvailable() {

        when(userRepository.findByCellphone("(31)98589-8952")).thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> CellphoneAvailable.verification(userRepository, "(31)98589-8952"));

        Mockito.verify(userRepository).findByCellphone("(31)98589-8952");
    }

}
