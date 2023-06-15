package com.project.clinicaapi.service.businessRule.commitDentist.registerDentist;

import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.repository.DentistRepository;

public record RegisterDentistArgs (DentistRegisterDTO dentistDTO, DentistRepository dentistRepository) {
}
