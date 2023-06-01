package com.project.clinicaapi.service.businessRule.LogFilter.validation;



import com.project.clinicaapi.entity.Log;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FilterByDateStartAndUser extends LogFilterVerification {

    public FilterByDateStartAndUser(LogFilterVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<Log> verification(LogFilterArgs args) {

        String dateStart = args.dateStart();

        String user = args.user();

        if (dateStart != null && user != null) {

            LocalDateTime startDay = converToLocalDateTime(dateStart, LocalTime.MIN);

            LocalDateTime endDay = converToLocalDateTime(dateStart, LocalTime.MAX);

            return args.logRepository().findPageByUsuarioAndDate(startDay, endDay, user, args.pageable());
        }

        return nextOne.verification(args);

    }

}
