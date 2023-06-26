package com.project.clinicaapi.util;

import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import com.project.clinicaapi.entity.WorkDay;

import java.util.Comparator;

public class MyWorkDayListDTOComparator implements Comparator<WorkDayResponseDTO> {

    @Override
    public int compare(WorkDayResponseDTO workDay1, WorkDayResponseDTO workDay2) {
        return workDay1.getIndex().compareTo(workDay2.getIndex());
    }

}
