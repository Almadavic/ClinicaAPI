package com.project.clinicaapi;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Factory {

    private final UserRepository userRepository;

    private final AppointmentRepository appointmentRepository;

    public User returnUserDataBaseByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }

    public Appointment returnAppointment() {
        return appointmentRepository.findAll().get(0);
    }


}
