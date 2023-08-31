package com.almada.clinicaapi.service.businessRule.LogFilter.validation;

import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FilterByDateStartAndDateEnd extends LogFilterVerification {

    public FilterByDateStartAndDateEnd(LogFilterVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<Log> verification(LogFilterArgs args) {

        String dateStart = args.dateStart();

        String dateEnd = args.dateEnd();

        if (dateStart != null && dateEnd != null) {

            LocalDateTime localDateTimeStart = converToLocalDateTime(dateStart, LocalTime.MIN);

            LocalDateTime localDateTimeEnd = converToLocalDateTime(dateEnd, LocalTime.MAX);

            dateOrder(localDateTimeStart, localDateTimeEnd, dateStart, dateEnd);

            return args.logRepository().findPageBetweenInterval(localDateTimeStart, localDateTimeEnd, args.pageable());
        }

        return nextOne.verification(args);

    }

}
