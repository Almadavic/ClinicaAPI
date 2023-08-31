package com.almada.clinicaapi.repository;

import com.almada.clinicaapi.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query(value = "SELECT * FROM tb_logs l where l.event_time BETWEEN :startday AND :endday", nativeQuery = true)
    Page<Log> findPageByDate(@Param(value = "startday") LocalDateTime startDay, @Param(value = "endday") LocalDateTime endDay, Pageable pageable);

    @Query(value = "SELECT * FROM tb_logs l where l.user_name = :user", nativeQuery = true)
    Page<Log> findPageByUsuario(@Param(value = "user") String user, Pageable pageable);

    @Query(value = "SELECT * FROM tb_logs l where l.event_time BETWEEN :startday AND :endday AND l.user_name = :user", nativeQuery = true)
    Page<Log> findPageByUsuarioAndDate(@Param(value = "startday") LocalDateTime startDay, @Param(value = "endday") LocalDateTime endDay,
                                       @Param(value = "user") String user, Pageable pageable);

    @Query(value = "SELECT * FROM tb_logs l where l.event_time BETWEEN :datestart AND :dateend", nativeQuery = true)
    Page<Log> findPageBetweenInterval(@Param(value = "datestart") LocalDateTime dateStart, @Param(value = "dateend") LocalDateTime dateEnd, Pageable pageable);

    @Query(value = "SELECT * FROM tb_logs l where l.event_time BETWEEN :datestart AND :dateend AND l.user_name = :user", nativeQuery = true)
    Page<Log> findPageBetweenIntervalAndUser(@Param(value = "datestart") LocalDateTime dateStart, @Param(value = "dateend") LocalDateTime dateEnd,
                                             @Param(value = "user") String user, Pageable pageable);

}
