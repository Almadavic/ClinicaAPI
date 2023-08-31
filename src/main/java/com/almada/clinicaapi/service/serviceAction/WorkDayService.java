package com.almada.clinicaapi.service.serviceAction;

import com.almada.clinicaapi.dto.response.WorkDayResponseDTO;
import com.almada.clinicaapi.entity.WorkDay;
import com.almada.clinicaapi.mapper.WorkDayMapper;
import com.almada.clinicaapi.repository.WorkDayRepository;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.util.MyWorkDayListComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkDayService {

    private final WorkDayRepository workDayRepository;

    private final WorkDayMapper mapper;

    public List<WorkDayResponseDTO> findAll() {

        List<WorkDay> workDays = workDayRepository.findAll();
        workDays.sort(new MyWorkDayListComparator());
        return mapper.toWorkDayResponseDTOList(workDays);
    }

    public WorkDayResponseDTO findByIndex(Integer index) {
        return mapper.toWorkDayResponseDTO(returnWorkDayDataBase(index));
    }

    public WorkDay returnWorkDayDataBase(Integer index) {
        return  workDayRepository.findByIndex(index)
                .orElseThrow(() -> new ResourceNotFoundException("The workday index: " + index + " wasn't found on database"));
    }

}
