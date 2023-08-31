package com.almada.clinicaapi.service.businessRule.LogFilter.validation;


import com.almada.clinicaapi.entity.Log;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterArgs;
import com.almada.clinicaapi.service.businessRule.LogFilter.LogFilterVerification;
import org.springframework.data.domain.Page;

public class FilterByUser extends LogFilterVerification {

    public FilterByUser(LogFilterVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<Log> verification(LogFilterArgs args) {

        String user = args.user();

        if (user != null) {
            return args.logRepository().findPageByUsuario(user, args.pageable());
        }

        return nextOne.verification(args);
    }

}
