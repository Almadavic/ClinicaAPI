package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 8)
@Component
@RequiredArgsConstructor
public class PatientAvailableTimeRegister {
}
