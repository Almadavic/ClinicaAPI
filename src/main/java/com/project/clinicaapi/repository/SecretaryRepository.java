package com.project.clinicaapi.repository;

import com.project.clinicaapi.entity.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecretaryRepository extends JpaRepository<Secretary, String> {

    Optional<Secretary> findByRegistration(String registration);

}
