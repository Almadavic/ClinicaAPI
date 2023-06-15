package com.project.clinicaapi.service.businessRule.commitDentist.updateDentist;

import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.serviceAction.WorkDayService;

public record UpdateDentistArgs(DentistUpdateDTO dentistDTO, Dentist dentist, DentistRepository dentistRepository, WorkDayService workDayService) {
}
