package com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class CroAvailableUpdate implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        String cro = args.dentistDTO().getCro();

        if (cro != null && !cro.equals(args.dentist().getCro())) {
            CommitDentistValidations.findDentistByCroValidation(args.dentistRepository(), cro);
        }

    }

}
