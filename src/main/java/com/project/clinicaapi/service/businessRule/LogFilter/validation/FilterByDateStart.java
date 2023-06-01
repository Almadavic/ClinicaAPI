package com.project.clinicaapi.service.businessRule.LogFilter.validation;


import com.project.clinicaapi.entity.Log;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FilterByDateStart extends LogFilterVerification {

    public FilterByDateStart(LogFilterVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<Log> verification(LogFilterArgs args) {

        String dateStart = args.dateStart();

        if (dateStart != null) {

            LocalDateTime startDay = converToLocalDateTime(dateStart, LocalTime.MIN);

            LocalDateTime endDay = converToLocalDateTime(dateStart, LocalTime.MAX);

            return args.logRepository().findPageByDate(startDay, endDay, args.pageable());
        }

        return nextOne.verification(args);

    }

}
