package com.project.clinicaapi.rest.dentistController;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class SaveDentistTest extends ClassTestParent {

    private final String path = "/dentists";

    @Test
    void croAlreadyExistsInTheSystem() throws Exception {

        String cro = "137185";

        DentistRegisterDTO dentistDTO = DentistRegisterDTO.builder()
                .login("newdentist")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newdentist@hotmail.com")
                .name("name nome")
                .cellphone("(61)98589-7484")
                .cro(cro)
                .speciality("othodontics")
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CroAlreadyRegisteredException))
                .andExpect(result -> assertEquals("The cro: " + cro + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidCroFormat() throws Exception {

        String cro = "13A99";

        DentistRegisterDTO dentistDTO = DentistRegisterDTO.builder()
                .login("newdentist1")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newdentist1@hotmail.com")
                .name("name nome")
                .cellphone("(61)98584-7484")
                .cro(cro)
                .speciality("othodontics")
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCroFormatException))
                .andExpect(result -> assertEquals("The cro: " + cro + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidSpecialtyValue() throws Exception {

        String specialty = "orthodo";

        DentistRegisterDTO dentistDTO = DentistRegisterDTO.builder()
                .login("newdentis2")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newdentist2@hotmail.com")
                .name("name nome")
                .cellphone("(61)98583-7484")
                .cro("81736")
                .speciality(specialty)
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidEnumValueException))
                .andExpect(result -> assertEquals("The value you sent: " + specialty + " to the type Specialty is not valid, valid values: "
                                + ListEnumValues.returnEnumValues(Arrays.asList(Specialty.values()))
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidWorkDayNumberSizeValue() throws Exception {

        DentistRegisterDTO dentistDTO = DentistRegisterDTO.builder()
                .login("newdentist3")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newdentist3@hotmail.com")
                .name("name nome")
                .cellphone("(61)98571-7484")
                .cro("81736")
                .speciality("orthodontics")
                .workDays(new HashSet<>(Arrays.asList(0, 9)))
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof WorkDayNumberSizeException))
                .andExpect(result -> assertEquals("The day of the week needs to be between 1 and 6",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void saveDentistSuccess() throws Exception {

        DentistRegisterDTO dentistDTO = DentistRegisterDTO.builder()
                .login("newdentist4")
                .address(new AddessRegisterDTO("country", "state", "city"))
                .email("newdentist4@hotmail.com")
                .name("name nome nome")
                .cellphone("(41)98565-7484")
                .cro("81700")
                .speciality("orthodontics")
                .workDays(new HashSet<>(Arrays.asList(1, 2, 3)))
                .gender("MALE")
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(created));

    }

}
