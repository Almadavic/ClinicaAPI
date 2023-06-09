package com.project.clinicaapi.repository;

import com.project.clinicaapi.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    Optional<WorkDay> findByIndex(Integer index);

}
