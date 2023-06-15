package com.project.clinicaapi.service.businessRule.commitPatient.registerPatient;

import com.project.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.project.clinicaapi.repository.PatientRepository;

public record RegisterPatientArgs(PatientRegisterDTO patientDTO, PatientRepository patientRepository) {
}
