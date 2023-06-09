package com.project.clinicaapi.util;

import com.project.clinicaapi.entity.Log;
import com.project.clinicaapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogRegistration {

    private final LogRepository logRepository;

    public void saveLog(String login, String event) {
        logRepository.save(new Log(login, event));
    }

}
