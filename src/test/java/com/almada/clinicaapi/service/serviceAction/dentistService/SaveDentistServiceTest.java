package com.almada.clinicaapi.service.serviceAction.dentistService;

import com.almada.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.almada.clinicaapi.dto.response.DentistResponseDTO;
import com.almada.clinicaapi.dto.response.WorkDayResponseDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.WorkDay;
import com.almada.clinicaapi.enumerated.WorkDayEnum;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.factory.WorkDayFactory;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.repository.WorkDayRepository;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.almada.clinicaapi.service.serviceAction.DentistService;
import com.almada.clinicaapi.service.serviceAction.EnableAccountService;
import com.almada.clinicaapi.service.serviceAction.WorkDayService;
import jakarta.transaction.Transactional;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class SaveDentistServiceTest {

    @Autowired
    private DentistService dentistService;

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private WorkDayRepository workDayRepository;

    @MockBean
    private EnableAccountService enableAccountService;


    @MockBean
    private List<RegisterUserVerification> registerUserVerifications;

    @MockBean
    private List<RegisterDentistVerification> registerDentistVerifications;

    @Autowired
    private DentistFactory dentistFactory;

    @BeforeEach
    void setUp() {
        workDayRepository.saveAll(Arrays.asList(
                new WorkDay(WorkDayEnum.MONDAY),
                new WorkDay(WorkDayEnum.TUESDAY),
                new WorkDay(WorkDayEnum.WEDNESDAY),
                new WorkDay(WorkDayEnum.THURSDAY),
                new WorkDay(WorkDayEnum.FRIDAY),
                new WorkDay(WorkDayEnum.SATURDAY)
        ));
    }

    @AfterEach
    void tearDown() {
        workDayRepository.deleteAll();
    }

    @Test
    @Transactional
    void fieldsValue() {

        DentistRegisterDTO dentistDTO = dentistFactory.dtoRegister();
        dentistDTO.setWorkDays(new HashSet<>(Arrays.asList(1, 2, 3)));

        Dentist dentist = dentistFactory.entity();

        DentistResponseDTO dentistResponseDTO = dentistService.save(dentistDTO, dentist);

        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(1))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(2))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(3))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(4))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(5))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(6))));

        workDayRepository.deleteAllDentistsWorkDays();
    }

}
