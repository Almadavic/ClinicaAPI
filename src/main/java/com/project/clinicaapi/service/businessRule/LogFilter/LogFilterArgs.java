package com.project.clinicaapi.service.businessRule.LogFilter;

import com.project.clinicaapi.repository.LogRepository;
import org.springframework.data.domain.Pageable;

public record LogFilterArgs(Pageable pageable, String user, String dateStart, String dateEnd, LogRepository logRepository) {

}
