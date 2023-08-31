package com.almada.clinicaapi.service.businessRule.commitSecretary.registerSecretary;

import com.almada.clinicaapi.dto.request.register.SecretaryRegisterDTO;
import com.almada.clinicaapi.repository.SecretaryRepository;

public record RegisterSecretaryArgs(SecretaryRegisterDTO secretaryDTO, SecretaryRepository secretaryRepository) {
}
