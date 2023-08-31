package com.almada.clinicaapi.repository;


import com.almada.clinicaapi.entity.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecretaryRepository extends JpaRepository<Secretary, String> {

    Optional<Secretary> findByRegistration(String registration);

}
