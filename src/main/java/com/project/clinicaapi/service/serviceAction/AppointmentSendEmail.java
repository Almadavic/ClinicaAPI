package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.util.ConvertingType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentSendEmail {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public void buildEmailMessage(Appointment appointment) {

        Patient patient = appointment.getPatient();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(patient.getEmail());
        message.setSubject("Consulta | Clinica API");
        message.setText(messageText(appointment));

        javaMailSender.send(message);
    }

    private String messageText(Appointment appointment) {

        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat(30) + "\n");
        sb.append("Caro paciente " + appointment.getPatient().getName() + ", sua nova consulta foi agendada. \n");
        sb.append(" Dia da consulta: " + ConvertingType.toBrazilFormat(appointment.getAppointmentDate()) + "("+ appointment.getWeekDay().name() + ")\n");
        sb.append("Horário : " + appointment.getTimeStart() + " - " + appointment.getTimeEnd()  + "\n");
        sb.append("\n");
        sb.append("-".repeat(30) + "\n");
        sb.append("Procedimento: " + appointment.getProcedure());
        return sb.toString();
    }

}
