package com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.validation;

import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 4)
@Component
public class UpdatingPatientData implements UpdatePatientVerification {

    @Override
    public void verification(UpdatePatientArgs args) {

        PatientUpdateDTO patientDTO = args.patientDTO();

        Patient patient = args.patient();

        setCpf(patientDTO, patient);

    }

    private void setCpf(PatientUpdateDTO patientDTO, Patient patient) {
        String cpf = patientDTO.getCpf();
        if(cpf != null) {
            patient.setCpf(cpf);
        }
    }

}
