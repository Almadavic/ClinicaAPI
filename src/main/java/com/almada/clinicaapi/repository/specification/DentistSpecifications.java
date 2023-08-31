package com.almada.clinicaapi.repository.specification;

import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.enumerated.Specialty;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class DentistSpecifications {

    private DentistSpecifications() {

    }

    public static Specification<Dentist> filter(String name, Boolean enabled, Specialty specialty) {
        return (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"));
            }

            if (enabled != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("enabled"), enabled));
            }

            if (specialty != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("specialty"), specialty));
            }

            return predicate;
        };
    }

}
