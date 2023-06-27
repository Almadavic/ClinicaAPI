package com.project.clinicaapi.rest.logController;

import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.DateOrderException;
import com.project.clinicaapi.service.customException.InvalidDateFormatException;
import com.project.clinicaapi.service.customException.ParameterMissingException;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class FindLogTest extends ClassTestParent {

    private final String path = "/logs";

    @Test
    void findLogByIdSuccess() throws Exception {

        mockMvc.perform(get(path + "/{id}", 1)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findLogByIdNotFound() throws Exception {

        Long id = 90L;

        mockMvc.perform(get(path + "/{id}", id)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The log id: " + id + " wasn't found on database",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void findPageLogsNoParameter() throws Exception {

        mockMvc.perform(get(path)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findLogByDateStartAndDateEnd() throws Exception {

        String timeStart = "06/05/2023";

        String timeEnd = "08/05/2023";


        mockMvc.perform(get(path + "?datestart={datestart}&dateend={dateend}", timeStart, timeEnd)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findLogByDateStartAndDateEndAndUserName() throws Exception {

        String timeStart = "06/05/2023";

        String timeEnd = "08/05/2023";

        String userName = "admin";


        mockMvc.perform(get(path + "?datestart={datestart}&datefim={datefim}&usuario={username}", timeStart, timeEnd, userName)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findLogByDateStart() throws Exception {

        String timeStart = "06/05/2023";


        mockMvc.perform(get(path + "?datestart={datestart}", timeStart)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findLogByUserName() throws Exception {

        String userName = "admin";


        mockMvc.perform(get(path + "?user={username}", userName)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findLogByDateStartAndUserName() throws Exception {

        String timeStart = "06/05/2023";

        String userName = "admin";


        mockMvc.perform(get(path + "?datestart={datestart}&user={user}", timeStart, userName)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(ok));

    }

    @Test
    void findLogByDateParametersMissingTimeStart() throws Exception {

        String tempoFim = "11/08/2023";

        mockMvc.perform(get(path + "?dateend={dateend}", tempoFim)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ParameterMissingException))
                .andExpect(result -> assertEquals("The parameter dateStart cannot be null in this query",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void findLogByDateWrongFormat() throws Exception {

        String dataInicio = "11/08/2023a";

        mockMvc.perform(get(path + "?datestart={datestart}", dataInicio)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidDateFormatException))
                .andExpect(result -> assertEquals("Invalid date format, Valid format: dd/MM/yyyy",
                        result.getResolvedException().getMessage()));

    }

    @Test
    void findLogByDateInvalidOrder() throws Exception {

        String dateStart = "11/08/2023";
        String dateEnd = "10/08/2023";


        mockMvc.perform(get(path + "?datestart={datestart}&dateend={dateend}", dateStart, dateEnd)
                        .header("Authorization", token("admin", "123456")))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DateOrderException))
                .andExpect(result -> assertEquals("The property 'datestart' value '" + dateStart + "' " +
                                "cannot be after the 'dateend' value '" + dateEnd + "'",
                        result.getResolvedException().getMessage()));

    }

}
