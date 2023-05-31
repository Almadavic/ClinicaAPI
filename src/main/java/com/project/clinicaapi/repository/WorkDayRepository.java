package com.project.clinicaapi.repository;

import com.project.clinicaapi.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
}
