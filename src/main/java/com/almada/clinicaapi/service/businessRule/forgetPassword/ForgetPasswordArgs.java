package com.almada.clinicaapi.service.businessRule.forgetPassword;

import com.almada.clinicaapi.repository.UserRepository;

public record ForgetPasswordArgs(String email, UserRepository userRepository) {
}
