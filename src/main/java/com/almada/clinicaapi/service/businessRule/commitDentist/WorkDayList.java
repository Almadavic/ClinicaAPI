package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.service.customException.WorkDayNumberSizeException;

import java.util.Set;

public class WorkDayList {

    private WorkDayList() {

    }

    public static void verification(Set<Integer> workDays) {

        if(workDays != null) {
            workDays.forEach(WorkDayList::verifyNumberSize);
        }

    }

    private static void verifyNumberSize(Integer number) {
        if (number < 1 || number > 6) {
            throw new WorkDayNumberSizeException();
        }
    }

}
