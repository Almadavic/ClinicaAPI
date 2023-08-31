package com.almada.clinicaapi.service.businessRule.LogFilter.validation;

import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FilterByDateStartAndDateEndAndUser extends LogFilterVerification {

    public FilterByDateStartAndDateEndAndUser(LogFilterVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<Log> verification(LogFilterArgs args) {

        String dateStart = args.dateStart();

        String dateEnd = args.dateEnd();

        String user = args.user();

        if (dateStart != null && dateEnd != null && user != null) {

            LocalDateTime localDateTimeStart = converToLocalDateTime(dateStart, LocalTime.MIN);

            LocalDateTime localDateTimeEnd = converToLocalDateTime(dateEnd, LocalTime.MAX);

            dateOrder(localDateTimeStart, localDateTimeEnd, dateStart, dateEnd);

            return args.logRepository().findPageBetweenIntervalAndUser(localDateTimeStart, localDateTimeEnd, user, args.pageable());
        }

        return nextOne.verification(args);
    }

}
