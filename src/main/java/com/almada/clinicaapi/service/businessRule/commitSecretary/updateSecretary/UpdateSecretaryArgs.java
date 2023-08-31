package com.almada.clinicaapi.service.businessRule.commitSecretary.updateSecretary;

import com.almada.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.almada.clinicaapi.entity.Secretary;
import com.almada.clinicaapi.repository.SecretaryRepository;

public record UpdateSecretaryArgs(SecretaryUpdateDTO secretaryDTO, Secretary secretary, SecretaryRepository secretaryRepository) {
}
