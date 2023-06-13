package com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.validation;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import com.project.clinicaapi.util.VerifyAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(value = 1)
@Component
public class NoFieldFilledUpdatePatient implements UpdatePatientVerification {

    @Override
    public void verification(UpdatePatientArgs args) {

        PatientUpdateDTO patientDTO = args.patientDTO();

        List<Object> attributes = Arrays.asList(patientDTO.getLogin(), patientDTO.getPassword(), patientDTO.getEmail(), patientDTO.getName(),
                patientDTO.getCellphone(), patientDTO.getCpf(), patientDTO.getGender(), patientDTO.getPasswordConfirmation());

        if (VerifyAttributes.commonAndAddressFieldsNull(attributes, patientDTO.getAddress())) {
            throw new NoFieldFilledException();
        }

    }

}
