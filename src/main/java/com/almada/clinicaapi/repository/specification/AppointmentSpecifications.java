package com.almada.clinicaapi.repository.specification;

import com.almada.clinicaapi.entity.Appointment;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AppointmentSpecifications {

    private AppointmentSpecifications() {

    }

    public static Specification<Appointment> filter(String dentistId, String patientId, LocalDate localDate, String patientName,
                                                    String dentistName) {
        return (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();

            if (dentistId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("dentist").get("id"), dentistId));
            }

            if (patientId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("patient").get("id"), patientId));
            }

            if(localDate !=null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("appointmentDate"), localDate));
            }

            if(patientName !=null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("patient").get("name"), patientName));
            }

            if(dentistName !=null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("dentist").get("name"), dentistName));
            }

            return predicate;
        };
    }

}
