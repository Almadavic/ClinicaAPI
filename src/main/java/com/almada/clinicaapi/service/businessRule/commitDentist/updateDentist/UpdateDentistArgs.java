package com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist;

import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.service.serviceAction.WorkDayService;

public record UpdateDentistArgs(DentistUpdateDTO dentistDTO, Dentist dentist, DentistRepository dentistRepository, WorkDayService workDayService) {
}
