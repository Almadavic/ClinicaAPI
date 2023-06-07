package com.project.clinicaapi.service.businessRule.commitUser.updateUser;

import com.project.clinicaapi.dto.request.update.UserUpdateDTO;
import com.project.clinicaapi.entity.User;

public record UpdateUserArgs(UserUpdateDTO userDTO, User user) {

}
