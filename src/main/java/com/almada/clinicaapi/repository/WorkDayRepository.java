package com.almada.clinicaapi.repository;


import com.almada.clinicaapi.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    Optional<WorkDay> findByIndex(Integer index);

    @Modifying
    @Query(value = "delete from tb_dentists_workdays", nativeQuery = true)
    void deleteAllDentistsWorkDays();

}
