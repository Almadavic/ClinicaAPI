package com.project.clinicaapi.dto;

import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.repository.UserRepository;

public interface UserDTOInterface {

    default User returnUserDataBase(String login, UserRepository userRepository) {
        return userRepository.findByLogin(login).get();
    }

}
