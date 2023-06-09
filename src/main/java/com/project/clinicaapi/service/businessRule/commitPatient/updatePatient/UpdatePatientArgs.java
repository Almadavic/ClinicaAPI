package com.project.clinicaapi.service.businessRule.commitPatient.updatePatient;

import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.entity.Patient;

public record UpdatePatientArgs(PatientUpdateDTO patientDTO, Patient patient) {
}
