package com.project.clinicaapi.service.dentistController;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
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
class SaveDentistServiceTest {

    @Autowired
    private DentistService dentistService;

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void fieldsValue() {

        DentistRegisterDTO dentistDTO = DentistRegisterDTO.builder()
                .login("newdentist")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newdentist@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7484")
                .cro("1738")
                .speciality("orthodontics")
                .workDays(new HashSet<>(Arrays.asList(1L, 2L, 3L)))
                .gender("MALE")
                .build();

        DentistResponseDTO dentistResponseDTO = dentistService.save(dentistDTO, returnUser());

        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(1L))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(2L))));
        Assertions.assertTrue(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(3L))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(4L))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(5L))));
        Assertions.assertFalse(dentistResponseDTO.getWorkDays().contains(new WorkDayResponseDTO(workDayService.returnWorkDayDataBase(6L))));

    }


    private User returnUser() {
        return userRepository.findByLogin("admin").get();
    }

}
