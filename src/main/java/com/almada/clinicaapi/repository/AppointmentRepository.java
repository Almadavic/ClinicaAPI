package com.almada.clinicaapi.repository;

import com.almada.clinicaapi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String>, JpaSpecificationExecutor<Appointment> {

    @Query(value = "SELECT * from tb_appointments a where a.dentist_id = :dentistid AND a.appointment_date = :appointmentdate ORDER BY a.appointment_date, time_start ASC"
            , nativeQuery = true)
    List<Appointment> findByDentistAndByDate(@Param(value = "dentistid")String dentistId, @Param(value = "appointmentdate")LocalDate appointmentDate);

    @Query(value = "SELECT * from tb_appointments a where a.patient_id = :patientid AND a.appointment_date = :appointmentdate ORDER BY a.appointment_date, time_start ASC"
            , nativeQuery = true)
    List<Appointment> findByPatientAndByDate(@Param(value = "patientid")String dentistId, @Param(value = "appointmentdate") LocalDate appointmentDate);

}
