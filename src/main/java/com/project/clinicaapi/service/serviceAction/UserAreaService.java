package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.dto.request.update.PatientUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.dto.request.update.UserUpdateDTO;
import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.dto.response.PatientResponseDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientArgs;
import com.project.clinicaapi.service.businessRule.commitPatient.updatePatient.UpdatePatientVerification;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryVerification;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserArgs;
import com.project.clinicaapi.service.businessRule.commitUser.updateUser.UpdateUserVerification;
import com.project.clinicaapi.util.LogRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAreaService {

    private final UserService userService;

    private final SecretaryService secretaryService;

    private final PatientService patientService;

    private final DentistService dentistService;

    private final List<UpdateUserVerification> updateUserVerifications;

    public UserResponseDTO myProfile(User userLogged) {
        return verifyInstance(userLogged);
    }

    public UserResponseDTO changeProfileDataAsUserGeneric(UserUpdateDTO updateData, User userLogged) {
        updateUserVerifications.forEach(v -> v.verification(new UpdateUserArgs(updateData, userLogged)));
        return userService.update(updateData, userLogged);
    }

    public SecretaryResponseDTO changeProfileDataAsSecretary(SecretaryUpdateDTO updateData, User userLogged) {
        return secretaryService.update(userLogged.getId(), updateData, userLogged);
    }

    public DentistResponseDTO changeProfileDataAsDentist(DentistUpdateDTO updateData, User userLogged) {
        return dentistService.update(userLogged.getId(), updateData, userLogged);
    }

    public PatientResponseDTO changeProfileDataAsPatient(PatientUpdateDTO updateData, User userLogged) {

        return patientService.update(userLogged.getId(), updateData, userLogged);
    }

    private UserResponseDTO verifyInstance(User userLogged) {

        if (userLogged instanceof Secretary secretary) {
            return new SecretaryResponseDTO(secretary);
        } else if (userLogged instanceof Dentist dentist) {
            return new DentistResponseDTO(dentist);
        } else if (userLogged instanceof Patient patient) {
            return new PatientResponseDTO(patient);
        } else {
            return new UserResponseDTO(userLogged);
        }

    }

}
