package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.repository.WorkDayRepository;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.MyWorkDayListComparator;
import com.project.clinicaapi.util.mapper.WorkDayMapper;
import jakarta.transaction.Transactional;
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

    public WorkDayResponseDTO findById(Long id) {
        return mapper.toWorkDayResponseDTO(returnWorkDayDataBase(id));
    }

    public WorkDay returnWorkDayDataBase(Long id) {
        return  workDayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The workday id: " + id + " wasn't found on database"));
    }

}
