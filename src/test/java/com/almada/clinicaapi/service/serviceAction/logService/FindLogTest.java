package com.almada.clinicaapi.service.serviceAction.logService;

import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.factory.LogFactory;
import com.almada.clinicaapi.repository.LogRepository;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.service.serviceAction.LogService;
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
class FindLogTest {

    @Autowired
    private LogFactory logFactory;

    @Autowired
    private LogService logService;

    @MockBean
    private LogRepository logRepository;

    private Log log;

    @BeforeEach
    void setUp() {
        this.log = logFactory.entity();
    }

    @Test
    void LogNotFoundById() {

        Long id = 100L;

        when(logRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> logService.findById(id));

        Mockito.verify(logRepository).findById(id);

    }

    @Test
    void LogFoundById() {

        when(logRepository.findById(log.getId())).thenReturn(Optional.of(log));

        Assertions.assertDoesNotThrow(() -> logService.findById(log.getId()));

        Mockito.verify(logRepository).findById(log.getId());

    }



}
