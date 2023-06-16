package com.project.clinicaapi.service.businessRule.disableAccount;

import com.project.clinicaapi.entity.User;

public record DisableAccountArgs (User userLogged, User toBeDisable) {
}
