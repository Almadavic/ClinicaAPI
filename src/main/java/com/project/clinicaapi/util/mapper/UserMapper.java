package com.project.clinicaapi.util.mapper;

import com.project.clinicaapi.dto.response.UserResponseDTO;
import com.project.clinicaapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toUserDTO(User user) {
        return new UserResponseDTO(user);
    }

    public Page<UserResponseDTO> toUserDTOPage(Page<User> users) {
        return users.map(UserResponseDTO::new);
    }

}
