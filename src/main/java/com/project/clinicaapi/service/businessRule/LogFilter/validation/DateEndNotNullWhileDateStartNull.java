package com.project.clinicaapi.service.businessRule.LogFilter.validation;

import com.project.clinicaapi.entity.Log;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import com.project.clinicaapi.service.customException.ParameterMissingException;
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
