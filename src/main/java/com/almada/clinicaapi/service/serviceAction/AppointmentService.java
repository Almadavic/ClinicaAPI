package com.almada.clinicaapi.service.serviceAction;


import com.almada.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.dto.response.AppointmentResponseDTO;
import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.mapper.AppointmentMapper;
import com.almada.clinicaapi.repository.AppointmentRepository;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.repository.specification.AppointmentSpecifications;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentSendEmail appointmentSendEmailService;

    private final AppointmentRepository appointmentRepository;

    private final DentistRepository dentistRepository;

    private final PatientRepository patientRepository;

    private final AppointmentMapper mapper;

    private final LogRegistration logRegistration;

    private final List<RegisterAppointmentVerification> registerAppointmentVerifications;

    private final List<UpdateAppointmentVerification> updateAppointmentVerifications;

    public AppointmentResponseDTO save(AppointmentRegisterDTO registerData, User userLogged) {
        Patient patient = returnPatientDataBase(registerData.getPatientId());
        Dentist dentist = returnDentistDataBase(registerData.getDentistId());
        registerAppointmentVerifications.forEach(v -> v.verification(new RegisterAppointmentArgs(registerData, dentist, patient, appointmentRepository)));
        Appointment appointment = mapper.toAppointmentEntity(registerData);
        setReferences(appointment, patient, dentist);
        AppointmentResponseDTO appointmentDTO = saveAndConvert(appointment);
        appointmentSendEmailService.buildEmailMessage(appointment);
        logRegistration.saveLog(userLogged.getUsername(), " registered the appointment: " + appointment.getId());
        return appointmentDTO;
    }

    public Page<AppointmentResponseDTO> findPage(Pageable pageable) {
        appointmentRepository.findAll(pageable);
        return mapper.toAppointmentDTOPage(appointmentRepository.findAll(pageable));
    }

    public AppointmentResponseDTO findById(String appointmentId) {
        return mapper.toAppointmentDTO(returnAppointmentDataBase(appointmentId));
    }

    public AppointmentResponseDTO update(String appointmentId, AppointmentUpdateDTO updateData, User userLogged) {
        Appointment appointment = returnAppointmentDataBase(appointmentId);
        updateAppointmentVerifications.forEach(v -> v.verification(new UpdateAppointmentArgs(updateData, appointment, appointmentRepository)));
        AppointmentResponseDTO appointmentDTO = saveAndConvert(appointment);
        appointmentSendEmailService.buildEmailMessage(appointment);
        logRegistration.saveLog(userLogged.getUsername(), "updated the appointment: " + appointment.getId());
        return appointmentDTO;
    }

    public Page<AppointmentResponseDTO> appointmentsByDentistId(String dentistId, LocalDate appointmentDate, String patientName,
                                                                Pageable pageable) {

        Specification<Appointment> spec = AppointmentSpecifications.filter(dentistId, null, appointmentDate, patientName, null);

        return mapper.toAppointmentDTOPage(appointmentRepository.findAll(spec, pageable));
    }

    public Page<AppointmentResponseDTO> appointmentsByPatientId(String patientId, LocalDate appointmentDate, String dentistName,
                                                                Pageable pageable) {

        Specification<Appointment> spec = AppointmentSpecifications.filter(null, patientId, appointmentDate, null, dentistName);

        return mapper.toAppointmentDTOPage(appointmentRepository.findAll(spec, pageable));
    }

    private AppointmentResponseDTO saveAndConvert(Appointment appointment) {
        return mapper.toAppointmentDTO(appointmentRepository.save(appointment));
    }

    private void setReferences(Appointment appointment, Patient patient, Dentist dentist) {
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
    }

    private Appointment returnAppointmentDataBase(String appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("The appointment id: " + appointmentId + " wasn't found on database"));
    }

    private Patient returnPatientDataBase(String patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("The patient id: " + patientId + " wasn't found on database"));
    }

    private Dentist returnDentistDataBase(String dentistId) {
        return dentistRepository.findById(dentistId)
                .orElseThrow(() -> new ResourceNotFoundException("The dentist id: " + dentistId + " wasn't found on database"));
    }

}
