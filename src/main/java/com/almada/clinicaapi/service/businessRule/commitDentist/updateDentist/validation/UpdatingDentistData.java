package com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.validation;

import com.almada.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.enumerated.Specialty;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.almada.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistVerification;
import com.almada.clinicaapi.service.serviceAction.WorkDayService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Order(value = 6)
@Component
public class UpdatingDentistData implements UpdateDentistVerification {

    @Override
    public void verification(UpdateDentistArgs args) {

        DentistUpdateDTO dentistDTO = args.dentistDTO();

        Dentist dentist = args.dentist();

        setCro(dentistDTO, dentist);
        setSpecialty(dentistDTO, dentist);
        setWorkDaysList(dentist, dentistDTO, args.workDayService());

    }

    private void setCro(DentistUpdateDTO dentistDTO, Dentist dentist) {
        String cro = dentistDTO.getCro();
        if (cro != null) {
            dentist.setCro(cro);
        }
    }

    private void setSpecialty(DentistUpdateDTO dentistDTO, Dentist dentist) {
        String specialty = dentistDTO.getSpeciality();
        if(specialty != null) {
            dentist.setSpecialty(Specialty.valueOf(specialty.toUpperCase()));
        }
    }

    private void setWorkDaysList(Dentist dentist, DentistUpdateDTO dentistDTO, WorkDayService workDayService) {

        Set<Integer> workDays = dentistDTO.getWorkDays();

        if(workDays != null) {
                dentist.getWorkDays().clear();
                dentistDTO.getWorkDays().forEach(workday ->
                        dentist.addWorkDay(workDayService.returnWorkDayDataBase(workday)));
        }

    }

}


