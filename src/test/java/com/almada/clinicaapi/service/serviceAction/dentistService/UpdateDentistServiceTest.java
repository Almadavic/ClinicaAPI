package com.almada.clinicaapi.service.serviceAction.dentistService;

import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.dto.response.DentistResponseDTO;
import com.almada.clinicaapi.dto.response.WorkDayResponseDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.entity.WorkDay;
import com.almada.clinicaapi.enumerated.WorkDayEnum;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.repository.WorkDayRepository;
import com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist.RegisterDentistVerification;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.almada.clinicaapi.service.serviceAction.DentistService;
import com.almada.clinicaapi.service.serviceAction.EnableAccountService;
import com.almada.clinicaapi.service.serviceAction.WorkDayService;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateDentistServiceTest {

    @Autowired
    private DentistService dentistService;

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private WorkDayRepository workDayRepository;

    @MockBean
    private EnableAccountService enableAccountService;

    @MockBean
    private List<UpdateUserVerification> updateUserVerifications;

    @MockBean
    private List<UpdateDentistVerification> updateDentistVerifications;

    @Autowired
    private DentistFactory dentistFactory;

    @Autowired
    private DentistRepository dentistRepository;

    private Dentist dentist;

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

        dentist = dentistRepository.save(dentistFactory.entity());
    }

    @AfterEach
    void tearDown() {
        dentistRepository.deleteAll();
    }

    @Test
    @Transactional
    void fieldsValue() {

        String cro = "1342";
        String specialty = "orthodontics";

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .cro(cro)
                .speciality(specialty)
                .workDays(new HashSet<>(Arrays.asList(1, 2, 3, 4)))
                .build();

        DentistResponseDTO dentistResponseDTO = dentistService.update(dentist.getId(), dentistDTO, dentistFactory.entity());

        Assertions.assertEquals(cro, dentistResponseDTO.getCro());
        Assertions.assertTrue(specialty.equalsIgnoreCase(dentistResponseDTO.getSpeciality()));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(1))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(2))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(3))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(4))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(5))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(6))));

        workDayRepository.deleteAllDentistsWorkDays();
    }

}
