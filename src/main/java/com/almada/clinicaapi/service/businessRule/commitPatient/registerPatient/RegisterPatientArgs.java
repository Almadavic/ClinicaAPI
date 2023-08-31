package com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient;

import com.almada.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.almada.clinicaapi.repository.PatientRepository;

public record RegisterPatientArgs(PatientRegisterDTO patientDTO, PatientRepository patientRepository) {
}
