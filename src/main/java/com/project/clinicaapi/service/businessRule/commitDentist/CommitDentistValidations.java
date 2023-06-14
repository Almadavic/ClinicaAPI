package com.project.clinicaapi.service.businessRule.commitDentist;

import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.enumerated.Gender;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class CommitDentistValidations {

    private CommitDentistValidations() {

    }

    public static void findDentistByCroValidation(DentistRepository dentistRepository, String cro) {

        Optional<Dentist> dentistOptional = dentistRepository.findByCro(cro);

        if (dentistOptional.isPresent()) {
            throw new CroAlreadyRegisteredException(cro);
        }

    }

    public static void croFormatValidation(String cro) {

        String regexCroValid = "^[0-9]+$";

        if (!cro.matches(regexCroValid)) {
            throw new InvalidCroFormatException(cro);
        }

    }

    public static void workdayListValidation(Set<Long> workDays) {

        if(workDays != null) {
            workDays.forEach(CommitDentistValidations::verifyNumberSize);
        }

    }

    private static void verifyNumberSize(long number) {
        if (number < 1 || number > 6) {
            throw new WorkDayNumberSizeException();
        }
    }

    public static Specialty specialtyValueValidation(String specialty) {
        try {
            if(specialty != null) {
                return Specialty.valueOf(specialty.toUpperCase());
            }
            return null;
        } catch (IllegalArgumentException exception) {
            throw new InvalidEnumValueException(specialty, "Specialty", ListEnumValues.returnEnumValues(Arrays.asList(Specialty.values())));
        }

    }

}
