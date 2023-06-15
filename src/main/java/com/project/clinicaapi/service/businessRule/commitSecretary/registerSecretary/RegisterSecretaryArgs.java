package com.project.clinicaapi.service.businessRule.commitSecretary.registerSecretary;

import com.project.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.project.clinicaapi.repository.SecretaryRepository;

public record RegisterSecretaryArgs(SecretaryRegisterDTO secretaryDTO, SecretaryRepository secretaryRepository) {
}
