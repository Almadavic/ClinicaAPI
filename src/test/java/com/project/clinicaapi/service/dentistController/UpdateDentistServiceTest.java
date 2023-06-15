package com.project.clinicaapi.service.dentistController;

import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.serviceAction.DentistService;
import com.project.clinicaapi.service.serviceAction.WorkDayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateDentistServiceTest {

    @Autowired
    private DentistService dentistService;

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void fieldsValue() {

        String cro = "1342";
        String specialty = "orthodontics";

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .cro(cro)
                .speciality(specialty)
                .workDays(new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L)))
                .build();

        DentistResponseDTO dentistResponseDTO = dentistService.update(returnDentistId(), dentistDTO, userLogged());

        Assertions.assertEquals(cro, dentistResponseDTO.getCro());
        Assertions.assertTrue(specialty.equalsIgnoreCase(dentistResponseDTO.getSpeciality()));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(1L))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(2L))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(3L))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(4L))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(5L))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(6L))));

    }

    private String returnDentistId() {
        return userRepository.findByLogin("dentist1").get().getId();
    }

    private User userLogged() {
        return userRepository.findByLogin("admin").get();
    }


}
