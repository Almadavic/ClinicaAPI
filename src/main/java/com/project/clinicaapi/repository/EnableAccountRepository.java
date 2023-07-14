package com.project.clinicaapi.repository;

import com.project.clinicaapi.entity.EnableAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnableAccountRepository extends JpaRepository<EnableAccount, Long> {

    Optional<EnableAccount> findByCode(String code);

}
