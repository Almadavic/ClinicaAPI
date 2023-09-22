package com.almada.clinicaapi.service.serviceAction.logService;

import com.almada.clinicaapi.dto.response.LogResponseDTO;
import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.repository.LogRepository;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.serviceAction.LogService;
import com.almada.clinicaapi.util.LogRegistration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ActiveProfiles(value = "test")
@SpringBootTest
class findLogPageTest {

    @Autowired
    private LogService logService;

    @Autowired
    private LogRegistration logRegistration;

    @Autowired
    private LogRepository logRepository;

     @BeforeEach
     void setUp() {
        logRegistration.saveLog("test1", "test1");
         logRegistration.saveLog("test2", "test2");
         logRegistration.saveLog("test3", "test3");
     }

     @AfterEach
     void tearDown() {
         logRepository.deleteAll();
     }

    @Test
    void findPageByUser() {

        String user = "user1";

        Page<LogResponseDTO> logsDTO = logService.findPage(returnPageable(), user, null, null);

        Assertions.assertTrue(matchUser(logsDTO, user));

    }

    @Test
    void findPageByDay() {

        String day = "08/05/2023";

        Page<LogResponseDTO> logsDTO = logService.findPage(returnPageable(), null, day, null);

        Assertions.assertTrue(matchDay(logsDTO, day));

    }

    @Test
    void findPageByTempoInicioAndTempoFim() {

        String timeStart = "04/12/2023";

        String timeEnd = "09/12/2023";


        Page<LogResponseDTO> logsDTO = logService.findPage(returnPageable(), null, timeStart, timeEnd);
        matchInterval(logsDTO, timeStart, timeEnd);
        Assertions.assertTrue(matchInterval(logsDTO, timeStart, timeEnd));

    }

    @Test
    void findPageByUserAndDay() {

        String user = "user1";

        String day = "09/05/2022";

        Page<LogResponseDTO> logsDTO = logService.findPage(returnPageable(), user, day, null);

        Assertions.assertTrue(matchUserAndDay(logsDTO, user, day));

    }

    @Test
    void findPageByUserAndTempoInicioAndTempoFimAndUser() {

        String user = "user1";

        String timeStart = "04/12/2023";

        String timeEnd = "09/12/2023";

        Page<LogResponseDTO> logsDTO = logService.findPage(returnPageable(), user, timeStart, timeEnd);

        Assertions.assertTrue(matchUserAndInterval(logsDTO, user, timeStart, timeEnd));

    }

    private boolean matchUser(Page<LogResponseDTO> logsDTO, String user) {

        boolean match = true;

        for (LogResponseDTO logDTO : logsDTO) {
            if (!logDTO.getUser().equals(user)) {
                match = false;
                break;
            }
        }

        return match;
    }

    private boolean matchDay(Page<LogResponseDTO> logsDTO, String day) {

        boolean match = true;

        for (LogResponseDTO logDTO : logsDTO) {
            if (!logDTO.getEventTime().toLocalDate().toString().equals(day)) {
                match = false;
                break;
            }
        }

        return match;
    }

    private boolean matchInterval(Page<LogResponseDTO> logsDTO, String tempoInicio, String tempoFim) {

        boolean match = true;

        for (LogResponseDTO logDTO : logsDTO) {
            if (logDTO.getEventTime().isAfter(toLocalDateTime(tempoFim)) || logDTO.getEventTime().isBefore(toLocalDateTime(tempoInicio))) {
                match = false;
                break;
            }
        }

        return match;
    }

    private boolean matchUserAndDay(Page<LogResponseDTO> logsDTO, String user, String day) {

        return matchUser(logsDTO, user) && matchDay(logsDTO, day);
    }

    private boolean matchUserAndInterval(Page<LogResponseDTO> logsDTO, String user, String tempoInicio, String tempoFim) {

        return matchUser(logsDTO, user) && matchInterval(logsDTO, tempoInicio, tempoFim);
    }

    private Pageable returnPageable() {
        return PageRequest.of(0, 10);
    }

    private LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

}
