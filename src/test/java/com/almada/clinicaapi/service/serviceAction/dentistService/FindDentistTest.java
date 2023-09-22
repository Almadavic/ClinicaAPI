package com.almada.clinicaapi.service.serviceAction.dentistService;

import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.DentistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class FindDentistTest {

    @Autowired
    private DentistFactory dentistFactory;

    @Autowired
    private DentistService dentistService;

    @MockBean
    private DentistRepository dentistRepository;

    private Dentist dentist;

    @BeforeEach
    void setUp() {
        this.dentist = dentistFactory.entity();
    }

    @Test
    void dentistNotFoundById() {

        String id = "idDoesntExist";

        when(dentistRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> dentistService.findById(id));

        Mockito.verify(dentistRepository).findById(id);

    }

    @Test
    void dentistFoundById() {

        when(dentistRepository.findById(dentist.getId())).thenReturn(Optional.of(dentist));

        Assertions.assertDoesNotThrow(() -> dentistService.findById(dentist.getId()));

        Mockito.verify(dentistRepository).findById(dentist.getId());

    }

    @Test
    void dentistNotFoundByCro() {

        String cro = "croDoesntExist";

        when(dentistRepository.findByCro(cro)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> dentistService.findByCro(cro));

        Mockito.verify(dentistRepository).findByCro(cro);

    }

    @Test
    void dentistFoundByCro() {

        when(dentistRepository.findByCro(dentist.getCro())).thenReturn(Optional.of(dentist));

        Assertions.assertDoesNotThrow(() -> dentistService.findByCro(dentist.getCro()));

        Mockito.verify(dentistRepository).findByCro(dentist.getCro());

    }

}
