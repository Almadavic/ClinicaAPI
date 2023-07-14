package com.project.clinicaapi;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.entity.EnableAccount;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.repository.EnableAccountRepository;
import com.project.clinicaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Factory {

    private final UserRepository userRepository;

    private final AppointmentRepository appointmentRepository;

    private final EnableAccountRepository enableAccountRepository;

    public User returnUserDataBaseByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }

    public Appointment returnAppointment() {
        return appointmentRepository.findAll().get(0);
    }

    public EnableAccount returnEnableAccountByCode(String code) {
        return enableAccountRepository.findByCode(code).get();
    }

}
