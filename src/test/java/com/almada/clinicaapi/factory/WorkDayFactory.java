package com.almada.clinicaapi.factory;

import com.almada.clinicaapi.entity.WorkDay;
import com.almada.clinicaapi.enumerated.WorkDayEnum;
import org.springframework.stereotype.Component;

@Component
public class WorkDayFactory {

    public WorkDay entity(WorkDayEnum workDay) {
        return WorkDay.builder()
                .workDay(workDay)
                .build();
    }

}
