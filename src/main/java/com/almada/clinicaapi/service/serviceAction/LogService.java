package com.almada.clinicaapi.service.serviceAction;


import com.almada.clinicaapi.dto.response.LogResponseDTO;
import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.mapper.LogMapper;
import com.almada.clinicaapi.repository.LogRepository;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import com.almada.clinicaapi.service.businessRule.LogFilter.validation.*;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
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
