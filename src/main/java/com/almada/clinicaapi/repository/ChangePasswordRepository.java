package com.almada.clinicaapi.repository;


import com.almada.clinicaapi.entity.ChangePassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChangePasswordRepository extends JpaRepository<ChangePassword, Long> {

    Optional<ChangePassword> findByCode(String code);

}
