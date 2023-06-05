package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.response.LogResponseDTO;
import com.project.clinicaapi.entity.Log;
import com.project.clinicaapi.repository.LogRepository;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import com.project.clinicaapi.service.businessRule.LogFilter.validation.*;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    private final LogMapper mapper;


    public Page<LogResponseDTO> findPage(Pageable pageable, String user, String dateStart, String dateEnd) {

        LogFilterVerification verification = new DateEndNotNullWhileDateStartNull(
                new FilterByDateStartAndDateEndAndUser(
                        new FilterByDateStartAndDateEnd(
                                new FilterByDateStartAndUser(
                                        new FilterByDateStart(
                                                new FilterByUser(
                                                        new NoFilters()))))));

        return mapper.toLogDTOPage(verification.verification(new LogFilterArgs(pageable, user, dateStart, dateEnd, logRepository)));
    }

    public LogResponseDTO findById(Long id) {
        return mapper.toLogDTO(returnLogDataBase(id));
    }

    private Log returnLogDataBase(Long id) {
        return logRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The log id: " + id + " wasn't found on database"));
    }

}
