package com.project.clinicaapi.repository;

import com.project.clinicaapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findByCellphone(String cellphone);

    @Query("select u from User u INNER JOIN u.enableAccount ea where ea.code = :code")
    User findUserInnerCode(String code);

}
