package com.project.clinicaapi.dto.request.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
public class DentistRegisterDTO extends UserRegisterDTO{

}
