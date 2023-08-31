package com.almada.clinicaapi.repository;

import com.almada.clinicaapi.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DentistRepository extends JpaRepository<Dentist, String>, JpaSpecificationExecutor<Dentist> {

    Optional<Dentist> findByCro(String cro);

}
