package com.almada.clinicaapi.service.businessRule.LogFilter.validation;

import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import com.almada.clinicaapi.service.customException.ParameterMissingException;
import org.springframework.data.domain.Page;

public class DateEndNotNullWhileDateStartNull extends LogFilterVerification {

    public DateEndNotNullWhileDateStartNull(LogFilterVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<Log> verification(LogFilterArgs args) {

        if (args.dateEnd() != null && args.dateStart() == null) {
            throw new ParameterMissingException("The parameter dateStart cannot be null in this query");
        }

        return nextOne.verification(args);

    }

}
