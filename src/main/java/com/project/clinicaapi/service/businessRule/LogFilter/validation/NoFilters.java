package com.project.clinicaapi.service.businessRule.LogFilter.validation;


import com.project.clinicaapi.entity.Log;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.project.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
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
