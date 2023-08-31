package com.almada.clinicaapi.util;


import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogRegistration {

    private final LogRepository logRepository;

    public void saveLog(String login, String event) {

        logRepository.save(new Log(login, event));
    }

}
