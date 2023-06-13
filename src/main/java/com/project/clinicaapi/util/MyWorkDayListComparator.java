package com.project.clinicaapi.util;

import com.project.clinicaapi.entity.WorkDay;

import java.util.Comparator;

public class MyWorkDayListComparator implements Comparator<WorkDay> {

    @Override
    public int compare(WorkDay workDay1, WorkDay workDay2) {
        return workDay1.getId().compareTo(workDay2.getId());
    }

}
