package com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient;

import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.repository.PatientRepository;

public record UpdatePatientArgs(PatientUpdateDTO patientDTO, Patient patient, PatientRepository patientRepository) {
}
