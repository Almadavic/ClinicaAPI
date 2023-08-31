package com.almada.clinicaapi.service.businessRule.commitUser.updateUser;

import com.almada.clinicaapi.dto.request.update.UserUpdateDTO;
import com.almada.clinicaapi.entity.User;

public record UpdateUserArgs(UserUpdateDTO userDTO, User user) {

}
