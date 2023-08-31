package com.almada.clinicaapi.service.serviceAction;

import com.almada.clinicaapi.dto.request.register.PatientRegisterDTO;
import com.almada.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.almada.clinicaapi.dto.response.PatientResponseDTO;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.mapper.PatientMapper;
import com.almada.clinicaapi.repository.PatientRepository;
import com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientArgs;
import com.almada.clinicaapi.service.businessRule.commitPatient.registerPatient.RegisterPatientVerification;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.almada.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.registerUser.RegisterUserVerification;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.almada.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.almada.clinicaapi.service.customException.ResourceNotFoundException;
import com.almada.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final EnableAccountService enableAccountService;

    private final PatientRepository patientRepository;

    private final PatientMapper mapper;

    private final LogRegistration logRegistration;

    private final List<RegisterUserVerification> registerUserVerifications;

    private final List<RegisterPatientVerification> registerPatientVerifications;

    private final List<UpdateUserVerification> updateUserVerifications;

    private final List<UpdatePatientVerification> updatePatientVerifications;

    public PatientResponseDTO save(PatientRegisterDTO registerData, User userLogged) {

        savePatientVerifications(registerData);

        Patient patient = mapper.toPatientEntity(registerData);

        PatientResponseDTO patientDTO = saveAndConvertToDTO(patient);
        enableAccountService.sendCodeToEmail(patient);

        logRegistration.saveLog(userLogged.getUsername(), "registered the patient: " + patient.getUsername());

        return patientDTO;
    }

    public Page<PatientResponseDTO> findPage(Pageable pageable) {
        return mapper.toPatientDTOPage(patientRepository.findAll(pageable));
    }

    public PatientResponseDTO findById(String patientId) {
        return mapper.toPatientDTO(returnPatientDataBase(patientId));
    }

    public PatientResponseDTO findByCpf(String cpf) {
        return mapper.toPatientDTO(
                patientRepository.findByCpf(cpf)
                        .orElseThrow(() -> new ResourceNotFoundException("The patient cpf: " + cpf + " wasn't found on database")));
    }


    public PatientResponseDTO update(String patientId, PatientUpdateDTO updateData, User userLogged) {

        Patient patient = returnPatientDataBase(patientId);

        updatePatientVerifications(updateData, patient);

        PatientResponseDTO patientDTO = saveAndConvertToDTO(patient);

        logRegistration.saveLog(userLogged.getUsername(), "updated the patient: " + patient.getUsername());

        return patientDTO;
    }

    private void savePatientVerifications(PatientRegisterDTO registerData) {
        registerUserVerifications.forEach(v -> v.verification(new RegisterUserArgs(registerData)));
        registerPatientVerifications.forEach(v -> v.verification(new RegisterPatientArgs(registerData, patientRepository)));
    }

    private void updatePatientVerifications(PatientUpdateDTO updateData, Patient patient) {
        updatePatientVerifications.forEach(v -> v.verification(new UpdatePatientArgs(updateData, patient, patientRepository)));
        updateUserVerifications.forEach(v -> v.verification(new UpdateUserArgs(updateData, patient)));
    }

    private PatientResponseDTO saveAndConvertToDTO(Patient patient) {
        return mapper.toPatientDTO(patientRepository.save(patient));
    }

    private Patient returnPatientDataBase(String patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("The patient id: " + patientId + " wasn't found on database"));
    }

}
