package com.almada.clinicaapi.service.businessRule.disableAccount;

import com.almada.clinicaapi.entity.User;

public record DisableAccountArgs (User userLogged, User toBeDisable) {
}
