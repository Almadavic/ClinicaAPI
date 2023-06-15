package com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary;

import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.entity.Secretary;
import com.project.clinicaapi.repository.SecretaryRepository;

public record UpdateSecretaryArgs(SecretaryUpdateDTO secretaryDTO, Secretary secretary, SecretaryRepository secretaryRepository) {
}
