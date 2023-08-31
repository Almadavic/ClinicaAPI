package com.almada.clinicaapi.mapper;

import com.almada.clinicaapi.dto.response.UserMonitoringResponseDTO;
import com.almada.clinicaapi.dto.response.UserResponseDTO;
import com.almada.clinicaapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public UserResponseDTO toUserDTO(User user) {
        return new UserResponseDTO(user);
    }

    public UserMonitoringResponseDTO toUserMonitoringDTO(User user) {
        UserMonitoringResponseDTO userMonitoringDTO = new UserMonitoringResponseDTO(user);
        addHateoas(userMonitoringDTO);
        return userMonitoringDTO;
    }

    public Page<UserMonitoringResponseDTO> toUserMonitoringDTOPage(Page<User> users) {
        Page<UserMonitoringResponseDTO> usersMonitoringDTO = users.map(UserMonitoringResponseDTO::new);
        usersMonitoringDTO.forEach(this::addHateoas);
        return usersMonitoringDTO;
    }

    private void addHateoas(UserResponseDTO userDTO) {

    }

}
