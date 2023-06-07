package com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary;

import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.entity.Secretary;

public record UpdateSecretaryArgs(SecretaryUpdateDTO secretaryDTO, Secretary secretary) {
}
