package com.project.clinicaapi.service.serviceAction;

import com.project.clinicaapi.dto.response.DentistResponseDTO;
import com.project.clinicaapi.dto.response.PatientResponseDTO;
import com.project.clinicaapi.dto.response.SecretaryResponseDTO;
import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAreaService {

    private final UserRepository userRepository;

    public UserResponseDTO myProfile(User user) {
        return verifyInstance(user);
    }

    private UserResponseDTO verifyInstance(User user) {

        if (user instanceof Secretary secretary) {
            return new SecretaryResponseDTO(secretary);
        } else if (user instanceof Dentist dentist) {
            return new DentistResponseDTO(dentist);
        } else if (user instanceof Patient patient) {
            return new PatientResponseDTO(patient);
        } else {
            return new UserResponseDTO(user);
        }

    }

}
