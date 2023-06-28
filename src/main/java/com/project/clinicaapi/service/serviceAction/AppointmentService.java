package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.response.AppointmentResponseDTO;
import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.ResourceNotFoundException;
import com.project.clinicaapi.util.LogRegistration;
import com.project.clinicaapi.util.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DentistRepository dentistRepository;

    private final PatientRepository patientRepository;

    private final AppointmentMapper mapper;

    private final LogRegistration logRegistration;

    private final List<RegisterAppointmentVerification> registerAppointmentVerifications;

    public AppointmentResponseDTO save(AppointmentRegisterDTO registerData, User userLogged) {
        Patient patient = returnPatientDataBase(registerData.getPatientId());
        Dentist dentist = returnDentistDataBase(registerData.getDentistId());
        registerAppointmentVerifications.forEach(v -> v.verification(new RegisterAppointmentArgs(registerData, dentist, patient, appointmentRepository)));
        Appointment appointment = mapper.toAppointmentEntity(registerData);
        setReferences(appointment, patient, dentist);
        AppointmentResponseDTO appointmentDTO = saveAndConvert(appointment);
        logRegistration.saveLog(userLogged.getUsername(), " registered the appointment: " + appointment.getId());
        return appointmentDTO;
    }

    public Page<AppointmentResponseDTO> findPage(Pageable pageable) {
        return mapper.toAppointmentDTOPage(appointmentRepository.findAll(pageable));
    }

    public AppointmentResponseDTO findById(String appointmentId) {
        return mapper.toAppointmentDTO(returnAppointmentDataBase(appointmentId));
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
