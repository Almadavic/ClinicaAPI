package com.almada.clinicaapi.service.businessRule.LogFilter.validation;


import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import org.springframework.data.domain.Page;

public class NoFilters extends LogFilterVerification {

    public NoFilters() {
        super(null);
    }

    @Override
    public Page<Log> verification(LogFilterArgs args) {
        return args.logRepository().findAll(args.pageable());
    }

}
