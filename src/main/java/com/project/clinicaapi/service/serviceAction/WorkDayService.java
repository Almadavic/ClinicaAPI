package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.response.WorkDayResponseDTO;
import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.repository.WorkDayRepository;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
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

    public WorkDayResponseDTO findByIndex(Integer index) {
        return mapper.toWorkDayResponseDTO(returnWorkDayDataBase(index));
    }

    public WorkDay returnWorkDayDataBase(Integer index) {
        return  workDayRepository.findByIndex(index)
                .orElseThrow(() -> new ResourceNotFoundException("The workday index: " + index + " wasn't found on database"));
    }

}
