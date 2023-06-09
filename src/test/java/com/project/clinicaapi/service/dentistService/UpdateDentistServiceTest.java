package com.project.clinicaapi.service.dentistService;

import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
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
                .workDays(new HashSet<>(Arrays.asList(1, 2, 3, 4)))
                .build();

        DentistResponseDTO dentistResponseDTO = dentistService.update(returnDentistId(), dentistDTO, userLogged());

        Assertions.assertEquals(cro, dentistResponseDTO.getCro());
        Assertions.assertTrue(specialty.equalsIgnoreCase(dentistResponseDTO.getSpeciality()));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(1))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(2))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(3))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(4))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(5))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(6))));

    }

    private String returnDentistId() {
        return userRepository.findByLogin("dentist1").get().getId();
    }

    private User userLogged() {
        return userRepository.findByLogin("admin").get();
    }


}
