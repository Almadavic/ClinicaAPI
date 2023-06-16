package com.project.clinicaapi.rest.dentistController;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class UpdateDentistTest extends ClassTestParent {

    @Autowired
    private UserRepository userRepository;

    private final String path = "/dentists";

    @Test
    void dentistByIdNotFound() throws Exception {

        String id = "aspjaioasjs9aasjassaas9sa";

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder().build();

        mockMvc.perform(patch(path + "/{dentistid}", id)
                        .header("Authorization", token("admin", "123456"))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The dentist id: " + id + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void croAlreadyExistsInTheSystem() throws Exception {

        String cro = "137185";

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .cro(cro)
                .build();


        mockMvc.perform(patch(path + "/" + returnDentistId())
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

        String cro = "137LL";

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .cro(cro)
                .build();

        mockMvc.perform(patch(path + "/" + returnDentistId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCroFormatException))
                .andExpect(result -> assertEquals("The cro: " + cro + " contains an invalid format"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void noFieldFilledToUpdate() throws Exception {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder().build();

        mockMvc.perform(patch(path + "/" + returnDentistId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }


    @Test
    void noWorkDayToUpdate() throws Exception {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .workDays(new HashSet<>())
                .build();

        mockMvc.perform(patch(path + "/" + returnDentistId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void invalidSpecialtyValue() throws Exception {

        String specialty = "orthodo";

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .speciality(specialty)
                .build();

        mockMvc.perform(patch(path + "/" + returnDentistId())
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

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .workDays(new HashSet<>(Arrays.asList(0, 9, -1)))
                .build();

        mockMvc.perform(patch(path + "/" + returnDentistId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof WorkDayNumberSizeException))
                .andExpect(result -> assertEquals("The day of the week needs to be between 1 and 6",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void updateDentistSuccess() throws Exception {

        DentistUpdateDTO dentistDTO = DentistUpdateDTO.builder()
                .cro("137319")
                .speciality("orthodontics")
                .workDays(new HashSet<>(Arrays.asList(1, 2, 3)))
                .build();


        mockMvc.perform(patch(path + "/" + returnDentistId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentistDTO)))
                .andExpect(status().is(ok));

    }

    private String returnDentistId() {
        return userRepository.findByLogin("dentist1").get().getId();
    }

}
