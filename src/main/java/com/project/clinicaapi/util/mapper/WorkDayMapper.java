package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import com.project.clinicaapi.entity.WorkDay;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkDayMapper {

    public WorkDayResponseDTO toWorkDayResponseDTO(WorkDay workDay) {
        return new WorkDayResponseDTO(workDay);
    }

    public List<WorkDayResponseDTO> toWorkDayResponseDTOList(List<WorkDay> workDays) {
        return workDays.stream().map(WorkDayResponseDTO::new).toList();
    }

}
