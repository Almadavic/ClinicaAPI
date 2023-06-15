package com.project.clinicaapi.service.businessRule.commitPatient.updatePatient;

import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.repository.PatientRepository;

public record UpdatePatientArgs(PatientUpdateDTO patientDTO, Patient patient, PatientRepository patientRepository) {
}
