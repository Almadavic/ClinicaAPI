package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
import com.almada.clinicaapi.service.serviceAction.AttributesListToUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class NoFieldFilledUpdateAppointment implements UpdateAppointmentVerification {

    private final AttributesListToUpdateService attributesListToUpdateService;

    @Override
    public void verification(UpdateAppointmentArgs args) {

        AppointmentUpdateDTO appointmentDTO = args.appointmentDTO();

        List<Object> attributes = Arrays.asList(appointmentDTO.getAppointmentDate(), appointmentDTO.getProcedure(), appointmentDTO.getTimeEnd(),
                appointmentDTO.getTimeStart());

        if(attributesListToUpdateService.allAttributesNull(attributes)) {
            throw new NoFieldFilledException();
        }

    }

}
