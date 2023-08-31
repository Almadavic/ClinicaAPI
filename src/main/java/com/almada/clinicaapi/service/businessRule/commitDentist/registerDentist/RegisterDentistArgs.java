package com.almada.clinicaapi.service.businessRule.commitDentist.registerDentist;

import com.almada.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.almada.clinicaapi.repository.DentistRepository;

public record RegisterDentistArgs (DentistRegisterDTO dentistDTO, DentistRepository dentistRepository) {
}
