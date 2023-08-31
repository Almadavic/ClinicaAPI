package com.almada.clinicaapi.util;

import com.almada.clinicaapi.entity.WorkDay;

import java.util.Comparator;

public class MyWorkDayListComparator implements Comparator<WorkDay> {

    @Override
    public int compare(WorkDay workDay1, WorkDay workDay2) {
        return workDay1.getIndex().compareTo(workDay2.getIndex());
    }

}
